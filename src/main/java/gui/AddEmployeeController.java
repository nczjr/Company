package gui;

import employee.*;
import generator.Generator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

import static employee.Role.*;
import static employee.Role.DEVELOPER;

public class AddEmployeeController {

    private ObservableList<Role> roles = FXCollections.observableArrayList(CEO, CONTRIBUTOR, DEVELOPER, TEAM_LEADER, TESTER);
    private ObservableList<String> universities = FXCollections.observableArrayList("AGH","UJ","WAT");
    private ObservableList<Sex> sexes = FXCollections.observableArrayList(Sex.FEMALE,Sex.MALE);
    private ObservableList<String> strategies =  FXCollections.observableArrayList("Only employees with gmail account","only employees from AGH","only employees from Poland","only women","only men","all employees can be hired");
    private Employee createdEmployee;
    private boolean successfulCreation = false;
    @FXML
    private void initialize(){
        role.setItems(roles);
        role.getSelectionModel().selectLast();
        university.setItems(universities);
        university.getSelectionModel().selectFirst();
        sex.setItems(sexes);
        sex.getSelectionModel().selectFirst();
        hireStrategy.setDisable(true);
        hireStrategy.setItems(strategies);
        hireStrategy.getSelectionModel().selectLast();
        maxNumOfEmployees.setDisable(true);
        role.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == CEO || newSelection == TEAM_LEADER){
                hireStrategy.setDisable(false);
                maxNumOfEmployees.setDisable(false);
            }else  {
                hireStrategy.setDisable(true);
                maxNumOfEmployees.setDisable(true);
            }
        });
    }

    @FXML
    private ChoiceBox<String> university;

    @FXML
    private Button addButton;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<Role> role;

    @FXML
    private ChoiceBox<Sex> sex;

    @FXML
    private TextField telephoneNumber;

    @FXML
    private TextField email;

    @FXML
    private TextField country;

    @FXML
    private TextField maxNumOfEmployees;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> hireStrategy;

    @FXML
    void add(ActionEvent event) {
        if (isValid()){
            Stage stage = (Stage) addButton.getScene().getWindow();
            switch (role.getValue()) {
                case DEVELOPER: createdEmployee = new Developer.Builder(name.getText(), DEVELOPER)
                        .university(university.getValue())
                        .sex(sex.getValue())
                        .country(country.getText())
                        .telephoneNumber(telephoneNumber.getText())
                        .email(email.getText())
                        .build();
                    successfulCreation = true;
                    stage.hide();
                break;
                case TESTER: createdEmployee = new Developer.Builder(name.getText(), TESTER)
                        .university(university.getValue())
                        .sex(sex.getValue())
                        .country(country.getText())
                        .telephoneNumber(telephoneNumber.getText())
                        .email(email.getText())
                        .build();
                    successfulCreation = true;
                    stage.hide();
                    break;
                case CONTRIBUTOR: createdEmployee = new Developer.Builder(name.getText(), CONTRIBUTOR)
                        .university(university.getValue())
                        .sex(sex.getValue())
                        .country(country.getText())
                        .telephoneNumber(telephoneNumber.getText())
                        .email(email.getText())
                        .build();
                    successfulCreation = true;
                    stage.hide();
                    break;
                case CEO: createdEmployee = new TeamManager.Builder(name.getText(), CEO, Integer.parseInt(maxNumOfEmployees.getText()))
                        .university(university.getValue())
                        .sex(sex.getValue())
                        .country(country.getText())
                        .telephoneNumber(telephoneNumber.getText())
                        .email(email.getText())
                        .hireStrategy(HireStrategy.getByName(hireStrategy.getValue()))
                        .build();
                    successfulCreation = true;
                    stage.hide();
                    break;
                case TEAM_LEADER: createdEmployee = new TeamManager.Builder(name.getText(), TEAM_LEADER, Integer.parseInt(maxNumOfEmployees.getText()))
                        .university(university.getValue())
                        .sex(sex.getValue())
                        .country(country.getText())
                        .telephoneNumber(telephoneNumber.getText())
                        .email(email.getText())
                        .hireStrategy(HireStrategy.getByName(hireStrategy.getValue()))
                        .build();
                    successfulCreation = true;
                    stage.hide();
                    break;

            }
        }else new InvalidInput("Invalid input! Fill all fields with correct data");
    }

    private boolean isValid() {
        return isStringValid(name.getText()) && isStringValid(country.getText()) && isEmailValid() && isNumberValid() && isNumberOfEmplValid();
    }

    private boolean isStringValid(String s) {
        String expression = "^[a-zA-Z\\s]+";
        return !s.isEmpty() && s.matches(expression);
    }

    private boolean isEmailValid() {
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return !email.getText().isEmpty() && email.getText().matches(pattern);

    }

    private boolean isNumberValid() {
        String pattern = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        return !telephoneNumber.getText().isEmpty() && telephoneNumber.getText().matches(pattern);
    }

    private boolean isNumberOfEmplValid() {
        String pattern = "^\\d$";
        if (maxNumOfEmployees.isDisabled())
            return true;
        else
            return !maxNumOfEmployees.getText().isEmpty() && maxNumOfEmployees.getText().matches(pattern);
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        successfulCreation = false;
        stage.hide();
    }


    public boolean isSuccessfulCreation() {
        return successfulCreation;
    }

    public Employee getCreatedEmployee() {
        return createdEmployee;
    }
}

