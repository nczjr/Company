package webApp;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import employee.Employee;
import employee.Role;
import employee.TeamManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MyUI extends UI {
    private Button generateButton = new Button("Generate company");
    private Button addButton = new Button("Hire employee");
    private Button fireButton = new Button("Fire employee");
    private Button assignButton = new Button("Assign task");
    private TreeGrid<Employee> grid = new TreeGrid<>(Employee.class);
    private List<Employee> employees = new ArrayList<>();
    private boolean isEmployeeSelected = false;
    private TreeDataProvider<Employee> provider = (TreeDataProvider<Employee>) grid.getDataProvider();
    private TreeData<Employee> data = provider.getTreeData();


    TreeGrid<Employee> getGrid() {
        return grid;
    }

    List<Employee> getEmployees() {
        return employees;
    }

    boolean isEmployeeSelected(){
        return isEmployeeSelected;
    }

    @Override
    protected void init(VaadinRequest request) {
        createLayout();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        generateButton.addClickListener(event -> {
            GenerateCompanyWindow window = new GenerateCompanyWindow(this);
            addWindow(window);

        });
        addButton.addClickListener(event -> {
            AddEmployeeWindow window = new AddEmployeeWindow(this);
            addWindow(window);

        });
        assignButton.addClickListener(event -> {
            AssignTaskWindow window = new AssignTaskWindow(this);
            addWindow(window);
        });
        fireButton.addClickListener(event -> {
            if (fireButton.isEnabled()){
                TeamManager manager = (TeamManager) data.getParent(getSelectedEmployee());
                manager.fire(getSelectedEmployee());
                data.removeItem(getSelectedEmployee());
                provider.refreshAll();
            }
        });
    }

    private void createLayout() {
        setGrid();
        fireButton.setEnabled(false);
        assignButton.setEnabled(false);
        HorizontalLayout layout = new HorizontalLayout(generateButton,addButton, fireButton,assignButton);
        Label mainLabel = new Label("Company Builder");
        mainLabel.setWidth("500px");
        VerticalLayout verticalLayout = new VerticalLayout(mainLabel,layout,grid);
        setContent(verticalLayout);
    }

    private void setGrid() {
        grid.setSizeFull();
        grid.setColumns("name", "role", "email", "telephoneNumber", "university", "country", "sex", "report");
        grid.addSelectionListener(event -> {
            isEmployeeSelected = true;
            fireButton.setEnabled(true);
            assignButton.setEnabled(true);
        });
    }

    Employee getSelectedEmployee() {
        return new ArrayList<>(grid.getSelectedItems()).get(0);
    }

    void refresh() {
        data.addItems(null,employees);
        employees.forEach(employee -> setEmployeesGrid(data,employee));
        provider.refreshAll();
    }

    private void setEmployeesGrid(TreeData<Employee> data,Employee employee) {
        if (employee.getRole() == Role.CEO || employee.getRole() == Role.TEAM_LEADER){
            ArrayList<Employee> coworkers = ((TeamManager) employee).getEmployeesArray();
            data.addItems(employee, coworkers);
            grid.expand(employee);
            for (Employee e :coworkers) {
                setEmployeesGrid(data,e);
            }
        }
    }

    void addHiredEmployee(Employee e, Employee toBeHired) {
        data.addItems(e,toBeHired);
        provider.refreshAll();
    }
}