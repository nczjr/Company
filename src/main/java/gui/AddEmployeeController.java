package gui;

import employee.Role;
import employee.Sex;
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

public class AddEmployeeController {

    private ObservableList<Role> roles = FXCollections.observableArrayList(Role.CEO,Role.CONTRIBUTOR,Role.DEVELOPER,Role.TEAM_LEADER,Role.TESTER);
    private ObservableList<String> universities = FXCollections.observableArrayList("AGH","UJ","WAT");
    private ObservableList<Sex> sexes = FXCollections.observableArrayList(Sex.FEMALE,Sex.MALE);
    private ObservableList<String> strategies =  FXCollections.observableArrayList("Only employees with gmail account","only employees from AGH","only employees from Poland","only women","only men","all employees can be hired");
    @FXML
    private void initialize(){
        role.setItems(roles);
        role.getSelectionModel().selectFirst();
        university.setItems(universities);
        university.getSelectionModel().selectFirst();
        sex.setItems(sexes);
        sex.getSelectionModel().selectFirst();
        hireStrategy.hide();
        hireStrategy.setItems(strategies);
        hireStrategy.getSelectionModel().selectLast();

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
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> hireStrategy;

    @FXML
    void add(ActionEvent event) {
        if (role.getSelectionModel().getSelectedItem() == Role.TEAM_LEADER ||role.getSelectionModel().getSelectedItem() == Role.CEO)
            hireStrategy.setDisable(false);
        if (isValid()){

        }else new InvalidInput("Invalid input! Fill all fields with correct data");
    }

    private boolean isValid() {
        return isNameValid() && isEmailValid() && isNumberValid();
    }

    private boolean isNameValid() {
        String expression = "^[a-zA-Z\\s]+";
        return !name.getText().isEmpty() && name.getText().matches(expression);
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

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();
    }


}

