package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GenerateCompanyViewController {

    MainViewController mainViewController;

    @FXML
    private TextField maxNumOfEmployees;

    @FXML
    private TextField size;

    @FXML
    private Button cancelButton;

    @FXML
    private Button generateButton;

    @FXML
    void addFunction(ActionEvent event) {

    }

    @FXML
    void generate(ActionEvent event) {
        Stage stage = (Stage) generateButton.getScene().getWindow();
        int sizeOfCompany = Integer.parseInt(size.getText());
        int maxNum = Integer.parseInt(maxNumOfEmployees.getText());
        stage.hide();
        stage.getOwner();
        mainViewController.showCompany(sizeOfCompany,maxNum);
    }

}

