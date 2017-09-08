package gui;

import employee.*;
import generator.Main;
import generator.Generator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import task.Report;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MainViewController {

    private Main main;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem generateCompanyButton;

    @FXML
    private MenuItem addEmployeeButton;

    @FXML
    private TreeView<String> tree;

    @FXML
    private TextArea personalDetails;

    @FXML
    private TextArea report;

    @FXML
    public void initialize(){

    }

    @FXML
    void addEmployee(ActionEvent event) throws IOException {
        personalDetails.setText("whateva");

        AnchorPane anchorPane = FXMLLoader.load(Main.class.getClassLoader().getResource("addEmployeeView.fxml"));
        Stage newStage = new Stage();
        newStage.setTitle("Add new employee");
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(Main.getPrimaryStage());
        Scene scene = new Scene(anchorPane);
        newStage.setScene(scene);
        newStage.showAndWait();
    }

    private void prepareNewItem(TeamManager teamManager,TreeItem rootItem){
            TreeItem<String> newManager = new TreeItem<>(teamManager.getName());
            newManager.setExpanded(true);
            addEmployees(newManager, teamManager);
            rootItem.getChildren().add(newManager);
    }

    private void addEmployees(TreeItem managerItem,TeamManager teamManager){
            for (Employee e : teamManager.getEmployeesArray()) {
                if (e.getRole() == Role.TEAM_LEADER)
                    prepareNewItem((TeamManager) e, managerItem);
                else managerItem.getChildren().add(new TreeItem<>(e.getName()));
            }
        }

    @FXML
    void generateCompany(ActionEvent event) {
        Generator.generate(3);
        ArrayList<TeamManager> employees = Generator.getManagers();
        TreeItem<String> root = new TreeItem<>("Company Name: ");
        tree.setRoot(root);
        root.setExpanded(true);

        TeamManager ceo = employees.get(0);
        TreeItem<String> managerItem = new TreeItem<>(ceo.getName());
        root.getChildren().add(managerItem);

        ArrayList<Employee> managerEmployees = ceo.getEmployeesArray();

        for (Employee e:managerEmployees) {
            managerItem.setExpanded(true);

            if (e.getRole() == Role.TEAM_LEADER) {
                prepareNewItem((TeamManager) e,managerItem);
            }else{
                    managerItem.getChildren().add(new TreeItem<>(e.getName()));
            }
            tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

                @Override
                public void changed(ObservableValue<? extends TreeItem<String>> observable,
                                    TreeItem<String> old_val, TreeItem<String> new_val) {
                    TreeItem<String> selectedItem = new_val;
                    System.out.println("Selected Text : " + selectedItem.getValue());
                    Optional<TeamManager> e = Generator.getManagers().stream().filter(empl -> empl.getName().equals(selectedItem.getValue())).findFirst();
                }

            });

        }   tree.setRoot(root);

    }

}
