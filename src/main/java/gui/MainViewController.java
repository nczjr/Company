package gui;

import employee.Developer;
import employee.Employee;
import employee.Role;
import employee.TeamManager;
import generator.Generator;
import generator.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainViewController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem generateCompanyButton;

    @FXML
    private MenuItem addEmployeeButton;

    @FXML
    private TreeTableView<Employee> tree;

    @FXML
    private TreeTableColumn<Employee,String> treeColumn;

    @FXML
    private TextArea personalDetails;

    @FXML
    private TextArea report;

    @FXML
    private void initialize(){
        tree = new TreeTableView<>();
        TreeItem<Employee> root = new TreeItem<>(new Developer(""));
        tree.setRoot(root);
        tree.setShowRoot(false);
        root.setExpanded(true);
    }
    @FXML
    void addEmployee(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(Main.class.getClassLoader().getResource("addEmployeeView.fxml"));
        Stage newStage = new Stage();
        newStage.setTitle("Add new employee");
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(anchorPane);
        newStage.setScene(scene);
        newStage.showAndWait();
    }

    @FXML
    void generateCompany(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("generateCompanyView.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Generate company");
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(anchorPane);
        newStage.setScene(scene);
        newStage.showAndWait();
    }

    private void prepareNewItem(TeamManager teamManager,TreeItem rootItem){
        TreeItem<Employee> newManager = new TreeItem<>(teamManager);
        newManager.setExpanded(true);
        addEmployees(newManager, teamManager);
        rootItem.getChildren().add(newManager);
    }

    private void addEmployees(TreeItem managerItem,TeamManager teamManager){
        for (Employee e : teamManager.getEmployeesArray()) {
            if (e.getRole() == Role.TEAM_LEADER)
                prepareNewItem((TeamManager) e, managerItem);
            else managerItem.getChildren().add(new TreeItem<>(e));
        }
    }


    public void showCompany(int size, int maxNumOfEmployees){
        Generator.generate(size,maxNumOfEmployees);
        ArrayList<TeamManager> employees = Generator.getManagers();
//        TreeItem<Employee> root = new TreeItem<>(new Developer(""));
//        tree.setRoot(root);
//        tree.setShowRoot(false);
//        root.setExpanded(true);

        TeamManager ceo = employees.get(0);
        TreeItem<Employee> managerItem = new TreeItem<>(ceo);

        tree.getRoot().getChildren().add(managerItem);

        ArrayList<Employee> managerEmployees = ceo.getEmployeesArray();

        for (Employee e:managerEmployees) {
            managerItem.setExpanded(true);

            if (e.getRole() == Role.TEAM_LEADER) {
                prepareNewItem((TeamManager) e,managerItem);
            }else{
                managerItem.getChildren().add(new TreeItem<>(e));
            }

        }   tree.setRoot(tree.getRoot());
        treeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Employee, String> param) -> param.getValue().getValue().getSimpleStringPropertyName());
        tree.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            report.setText(newSelection.getValue().reportWork().toString());
            fillPersonalDetails(newSelection);
        });
    }

    private void fillPersonalDetails(TreeItem<Employee> selectedItem){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ").append(selectedItem.getValue().getName()).append("\n");
        stringBuilder.append("Role: ").append(selectedItem.getValue().getRole()).append("\n");
        stringBuilder.append("University: ").append(selectedItem.getValue().getUniversity()).append("\n");
        stringBuilder.append("Email: ").append(selectedItem.getValue().getEmail()).append("\n");
        stringBuilder.append("Telephone number: ").append(selectedItem.getValue().getTelephoneNumber()).append("\n");
        stringBuilder.append("Country: ").append(selectedItem.getValue().getCountry()).append("\n");
        stringBuilder.append("Sex: ").append(selectedItem.getValue().getSex()).append("\n");

        if (selectedItem.getValue().getRole() == Role.TEAM_LEADER || selectedItem.getValue().getRole() == Role.CEO) {
            TeamManager teamManager = (TeamManager) selectedItem.getValue();
            stringBuilder.append("Currently has: ").append(teamManager.getEmployeesArray().size()).append(" employees \n");
            stringBuilder.append("Hire strategy: ").append(teamManager.getHireStrategy()).append("\n");
            stringBuilder.append("Max number of employees: ").append(teamManager.getMaxNumEmployes()).append("\n");
        }

        personalDetails.setText(stringBuilder.toString());
    }



}
