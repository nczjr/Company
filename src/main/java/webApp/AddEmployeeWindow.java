package webApp;


import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import employee.*;

import java.util.EnumSet;

class AddEmployeeWindow extends Window {
    private TextField name = new TextField("Name");
    private ComboBox<Role> role = new ComboBox<>("Role");
    private TextField email = new TextField("Email");
    private TextField telephoneNumber = new TextField("Telephone number");
    private TextField university = new TextField("University");
    private TextField country = new TextField("Country");
    private ComboBox<Sex> sex = new ComboBox<>("Sex");
    private TextField maxNumOfEmployees = new TextField("Max number of employees");
    private ComboBox<String> hireStrategy = new ComboBox<>("Hire strategy");
    private Button addButton = new Button("Add");
    private Button cancelButton = new Button("Cancel");
    private Label gap = new Label();
    private Binder<Employee> employeeBinder = new Binder<>();



    AddEmployeeWindow(MyUI myUI) {
        role.setItems(EnumSet.allOf(Role.class));
        maxNumOfEmployees.setEnabled(false);
        hireStrategy.setEnabled(false);
        VerticalLayout layout = new VerticalLayout(name,role,email,telephoneNumber,university,country,sex,maxNumOfEmployees,hireStrategy);
        HorizontalLayout buttons = new HorizontalLayout(addButton,gap,cancelButton);
        layout.addComponent(buttons);
        setSize();
        setContent(layout);
        center();
        role.addValueChangeListener(event -> {
            if (role.getValue() == Role.TEAM_LEADER || role.getValue() == Role.CEO){
                maxNumOfEmployees.setEnabled(true);
                hireStrategy.setEnabled(true);
                hireStrategy.setItems(HireStrategy.getPredicates());
            }else {
                maxNumOfEmployees.setEnabled(false);
                hireStrategy.setEnabled(false);
            }
        });
        cancelButton.addClickListener(event -> close());
        addButton.addClickListener(event -> {

        });
    }

    private void setSize() {
        name.setWidth("300px");
        role.setWidth("300px");
        email.setWidth("300px");
        telephoneNumber.setWidth("300px");
        university.setWidth("300px");
        country.setWidth("300px");
        sex.setWidth("300px");
        maxNumOfEmployees.setWidth("300px");
        hireStrategy.setWidth("300px");
        addButton.setWidth("130px");
        cancelButton.setWidth("130px");
        gap.setWidth("20px");

    }
}
