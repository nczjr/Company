package gui;

import employee.Role;
import employee.Sex;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddEmployeeController {

    private ObservableList<Role> roles = FXCollections.observableArrayList(Role.CEO,Role.CONTRIBUTOR,Role.DEVELOPER,Role.TEAM_LEADER,Role.TESTER);
    private ObservableList<String> universities = FXCollections.observableArrayList("AGH","UJ","WAT");
    private ObservableList<Sex> sexes = FXCollections.observableArrayList(Sex.FEMALE,Sex.MALE);

    @FXML
    private void initialize(){
        role.setItems(roles);
        university.setItems(universities);
        sex.setItems(sexes);
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
    private ChoiceBox<?> hireStrategy;

    @FXML
    void addFunction(ActionEvent event) {

    }

    @FXML
    void cancelFunction(ActionEvent event) {

    }


}

