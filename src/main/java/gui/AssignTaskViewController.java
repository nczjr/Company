package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AssignTaskViewController {


    @FXML
    private TextField unitsOfWork;

    @FXML
    private TextField task;

    @FXML
    private Button cancelButton;

    @FXML
    private Button assignButton;

    private String taskName;

    private int workUnits;

    private boolean taskCreated = false;

    private String invalidInputString = "";
    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void assign(ActionEvent event) {
        if (isValid()) {
            taskName = task.getText();
            workUnits = Integer.parseInt(unitsOfWork.getText());
            Stage stage = (Stage) assignButton.getScene().getWindow();
            taskCreated = true;
            stage.hide();
        } else new InvalidInput(invalidInputString);
    }

    private boolean isValid() {
        return isTaskValid() && isWorkUnitValid();
    }


    private boolean isTaskValid() {
        String expression = "^[a-zA-Z\\s]+";
        if (!task.getText().isEmpty() && task.getText().matches(expression))
            return true;
        else {
            invalidInputString = "Task name must be a string";
            return false;
        }
    }

    private boolean isWorkUnitValid() {
        String pattern = "^\\d$";
        if (!unitsOfWork.getText().isEmpty() && unitsOfWork.getText().matches(pattern))
            return true;
        else {
            invalidInputString = "Units of work field must be an integer";
            return false;
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public int getWorkUnits() {
        return workUnits;
    }

    public boolean isTaskCreated() {
        return taskCreated;
    }
}
