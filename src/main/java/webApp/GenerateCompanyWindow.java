package webApp;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import employee.Employee;
import employee.TeamManager;
import generator.Generator;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

class GenerateCompanyWindow extends Window{

    private final TextField size = new TextField("Size of comapny's strucutre");
    private final TextField maxNumOfEmployees = new TextField("Max number of employees per manager");
    private final Button closeBtn = new Button("Close");
    private final Button genereateBtn = new Button("Generate");
    private final HorizontalLayout buttons = new HorizontalLayout(genereateBtn,closeBtn);
    private final VerticalLayout layout = new VerticalLayout(size,maxNumOfEmployees,buttons);
    private MyUI myUI;

    GenerateCompanyWindow(MyUI myUI){
        super("Generate company");
        this.myUI = myUI;
        genereateBtn.setEnabled(false);
        genereateBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setContent(layout);
        center();
        setValueChangeListeners();
        setOnClickListeners();

    }

    private void setOnClickListeners() {
        genereateBtn.addClickListener(event -> {
            if (isEnabled()) {
                Generator.generate(Integer.parseInt(size.getValue()),Integer.parseInt(maxNumOfEmployees.getValue()));
                close();
                List<Employee> employees = new LinkedList<>(Generator.getManagers());
                TeamManager ceo = (TeamManager) employees.get(0);
                myUI.getEmployees().add(ceo);
                myUI.refresh();
            }
        });
        closeBtn.addClickListener(event -> close());
    }

    private void setValueChangeListeners() {
        size.addValueChangeListener(event -> checkValidation());
        maxNumOfEmployees.addValueChangeListener(event -> checkValidation());
    }


    private boolean isValid(TextField textField) {
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(textField.getValue()).matches() && !textField.getValue().isEmpty();
    }

    private void checkValidation(){
        if(isValid(size) && isValid(maxNumOfEmployees))
            genereateBtn.setEnabled(true);
        else {
            Notification.show("Input must be an integer and cannot be empty");
            genereateBtn.setEnabled(false);
        }
    }


}
