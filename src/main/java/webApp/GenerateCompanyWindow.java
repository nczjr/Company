package webApp;

import com.vaadin.ui.*;
import employee.Employee;
import employee.TeamManager;
import generator.Generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class GenerateCompanyWindow extends Window{

    private final TextField numOfManagers = new TextField("Number of managers");
    private final TextField maxNumOfEmployees = new TextField("Max number of employees per manager");
    private final Button closeBtn = new Button("Close");
    private final Button genereateBtn = new Button("Generate");
    private final HorizontalLayout buttons = new HorizontalLayout(genereateBtn,closeBtn);
    private final VerticalLayout layout = new VerticalLayout(numOfManagers,maxNumOfEmployees,buttons);


    GenerateCompanyWindow(MyUI myUI){
        super("Generate company");
        genereateBtn.setEnabled(false);
        setContent(layout);
        center();
        numOfManagers.addValueChangeListener(event -> checkValidation());
        maxNumOfEmployees.addValueChangeListener(event -> checkValidation());
        genereateBtn.addClickListener(event -> {
            if (isEnabled()) {
                Generator.generate(Integer.parseInt(numOfManagers.getValue()),Integer.parseInt(maxNumOfEmployees.getValue()));
                close();
                List<Employee> employees = new LinkedList<>();
                List<TeamManager> managers = new ArrayList<>(Generator.getManagers());
                for (TeamManager t:managers) {
                    employees.add(t);
                    employees.addAll(new ArrayList<>(t.getEmployeesArray()));
                }
                myUI.getEmployeeGrid().setItems(employees);

            }
        });
        closeBtn.addClickListener(event -> close());

    }


    private boolean isValid(TextField textField) {
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(textField.getValue()).matches() && !textField.getValue().isEmpty();
    }

    private void checkValidation(){
        if(isValid(numOfManagers) && isValid(maxNumOfEmployees))
            genereateBtn.setEnabled(true);
        else {
            Notification.show("Input must be an integer and cannot be empty");
            genereateBtn.setEnabled(false);
        }
    }


}
