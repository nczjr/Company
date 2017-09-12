package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

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

    private int sizeOfCompany;
    private int maxNum;

    boolean canCreate() {
        return canCreate;
    }

    private boolean canCreate = false;



    @FXML
    void addFunction(ActionEvent event){

    }
    @FXML
    void cancel(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void generate(ActionEvent event) {
        Stage stage = (Stage) generateButton.getScene().getWindow();
        if (isValid()) {
            canCreate = true;
            sizeOfCompany = Integer.parseInt(size.getText());
            maxNum = Integer.parseInt(maxNumOfEmployees.getText());
            stage.hide();
        }else{
            canCreate = false;
            new InvalidInput("Values must be numbers");
        }

    }

    int getSizeOfCompany() {
        return sizeOfCompany;
    }

    int getMaxNum() {
        return maxNum;
    }

    private boolean isValid() {
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(size.getText()).matches()
                && pattern.matcher(maxNumOfEmployees.getText()).matches();
    }
}

