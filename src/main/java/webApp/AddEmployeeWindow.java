package webApp;


import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;
import employee.*;
import task.Report;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static employee.Role.*;

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
    private MyUI myUI;
    private Binder<EmployeeToBuild> employeeBinder = new Binder<>(EmployeeToBuild.class);
    private EmployeeToBuild employeeToBuild = new EmployeeToBuild();




    AddEmployeeWindow(MyUI myUI) {
        this.myUI = myUI;
        setFields();
        setOnValueChangeListeners();
        setOnClickListeners();
        VerticalLayout layout = new VerticalLayout(name,role,email,telephoneNumber,university,country,sex,maxNumOfEmployees,hireStrategy);
        HorizontalLayout buttons = new HorizontalLayout(addButton,gap,cancelButton);
        layout.addComponent(buttons);
        setSize();
        setContent(layout);
        center();
        setBinder();
    }

    private void setOnClickListeners() {
        cancelButton.addClickListener(event -> close());
        addButton.addClickListener(event -> {
            if (employeeBinder.writeBeanIfValid(employeeToBuild)) {
                myUI.getEmployees().add(getEmployee());
                myUI.refresh();
                close();
            } else Notification.show("Cannot add such an employee");
        });
    }

    private void setOnValueChangeListeners() {
        role.addValueChangeListener(event -> {
            if (role.getValue() == Role.TEAM_LEADER || role.getValue() == Role.CEO){
                maxNumOfEmployees.setVisible(true);
                hireStrategy.setVisible(true);
                hireStrategy.setItems(HireStrategy.getPredicates());
            }else {
                maxNumOfEmployees.setVisible(false);
                hireStrategy.setVisible(false);
            }
        });
    }

    private void setFields() {
        role.setItems(EnumSet.allOf(Role.class));
        role.setValue(Role.CONTRIBUTOR);
        sex.setItems(EnumSet.allOf(Sex.class));
        sex.setValue(Sex.FEMALE);
        maxNumOfEmployees.setEnabled(false);
        hireStrategy.setEnabled(false);
        maxNumOfEmployees.setVisible(false);
        hireStrategy.setVisible(false);
    }

    private Employee getEmployee() {
        switch (role.getValue()){
            case DEVELOPER: return employeeToBuild.getDeveloper(DEVELOPER);
            case CONTRIBUTOR: return employeeToBuild.getDeveloper(CONTRIBUTOR);
            case TESTER: return employeeToBuild.getDeveloper(TESTER);
            case CEO: return employeeToBuild.getTeamManager(CEO);
            default: return employeeToBuild.getTeamManager(CEO);
        }
    }

    private void setBinder() {
        employeeBinder.forField(name)
                .withValidator(n -> n.matches("^[A-ZĄąĆćĘęŁłŃńÓóŚśŹźŻża-z', ]+$"),"Name must be a string and cannot be empty")
                .bind(EmployeeToBuild::getName,EmployeeToBuild::setName);
        employeeBinder.forField(role)
                .bind(EmployeeToBuild::getRole,EmployeeToBuild::setRole);
        employeeBinder.forField(email)
                .withValidator(e -> e.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"), "Wrong email format")
                .bind(EmployeeToBuild::getEmail,EmployeeToBuild::setEmail);
        employeeBinder.forField(telephoneNumber)
                .withValidator(t -> t.matches("^\\+(?:[0-9] ?){6,14}[0-9]$"),"Wrong number format")
                .bind(EmployeeToBuild::getTelephoneNumber,EmployeeToBuild::setTelephoneNumber);
        employeeBinder.forField(country)
                .withValidator(c -> c.matches("^[a-zA-Z\\s]+"), "Country must be a string and cannot be empty")
                .bind(EmployeeToBuild::getCountry,EmployeeToBuild::setCountry);
        employeeBinder.forField(university)
                .withValidator(c -> c.matches("^[a-zA-Z\\s]+"), "University must be a string and cannot be empty")
                .bind(EmployeeToBuild::getUniversity,EmployeeToBuild::setUniversity);
        employeeBinder.forField(sex)
                .bind(EmployeeToBuild::getSex,EmployeeToBuild::setSex);
        employeeBinder.forField(maxNumOfEmployees)
                .withValidator(m -> checkMaxNum(), "Max number of employees must be an integer")
                .bind(EmployeeToBuild::getMaxNumEmployes,EmployeeToBuild::setMaxNumEmployes);
        employeeBinder.forField(hireStrategy)
                .withValidator(event -> checkHireStrategy(), "Hire strategy must be specified")
                .bind(EmployeeToBuild::getHireStrategy,EmployeeToBuild::setHireStrategy);

    }

    private boolean checkMaxNum() {
        return maxNumOfEmployees.isEnabled() && !maxNumOfEmployees.isEmpty()
                && maxNumOfEmployees.getValue().matches("^\\d$");
    }

    private boolean checkHireStrategy() {
        return hireStrategy.isEnabled() && !hireStrategy.isEmpty();
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

    class EmployeeToBuild {
        String name;
        Role role;
        String university;
        Sex sex;
        String country;
        String telephoneNumber;
        String email;
        String maxNumEmployes;
        String hireStrategy;

        Developer getDeveloper(Role role){
            return new Developer.Builder(name, role)
                    .university(university)
                    .sex(sex)
                    .country(country)
                    .telephoneNumber(telephoneNumber)
                    .email(email)
                    .build();
        }

        TeamManager getTeamManager(Role role) {
            return new TeamManager.Builder(name, role, Integer.parseInt(maxNumEmployes))
                    .university(university)
                    .sex(sex)
                    .country(country)
                    .telephoneNumber(telephoneNumber)
                    .email(email)
                    .hireStrategy(HireStrategy.getByName(hireStrategy))
                    .build();
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        Role getRole() {
            return role;
        }

        void setRole(Role role) {
            this.role = role;
        }

        String getUniversity() {
            return university;
        }

        void setUniversity(String university) {
            this.university = university;
        }

        Sex getSex() {
            return sex;
        }

        void setSex(Sex sex) {
            this.sex = sex;
        }

        String getCountry() {
            return country;
        }

        void setCountry(String country) {
            this.country = country;
        }

        String getTelephoneNumber() {
            return telephoneNumber;
        }

        void setTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }

        String getEmail() {
            return email;
        }

        void setEmail(String email) {
            this.email = email;
        }

        String getMaxNumEmployes() {
            return maxNumEmployes;
        }

        void setMaxNumEmployes(String maxNumEmployes) {
            this.maxNumEmployes = maxNumEmployes;
        }

        String getHireStrategy() {
            return hireStrategy;
        }

        void setHireStrategy(String hireStrategy) {
            this.hireStrategy = hireStrategy;
        }
    }
}
