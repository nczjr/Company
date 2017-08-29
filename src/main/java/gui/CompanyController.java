package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;

public class CompanyController {

    @FXML
    private MenuItem generateCompanyButton;

    @FXML
    private MenuItem addEmployeeButton;

    @FXML
    private TreeView<?> tree;

    @FXML
    private TextArea personalDetails;

    @FXML
    private TextArea report;

    @FXML
    void addEmployee(ActionEvent event) {
        personalDetails.setText("whateva");

    }

    @FXML
    void generateCompany(ActionEvent event) {

    }

}
