package webApp;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import employee.Employee;
import generator.Generator;
import gui.AddEmployeeController;
import org.apache.tools.ant.Project;

import java.util.stream.Stream;

public class MyUI extends UI {
    private Button generateButton = new Button("Generate company");
    private Button addButton = new Button("Add employee");
    private Button hireButton = new Button("Fire employee");
    private Button assignButton = new Button("Assign task");
    private Grid<Employee> employeeGrid = new Grid<>(Employee.class);


    Grid<Employee> getEmployeeGrid() {
        return employeeGrid;
    }


    @Override
    protected void init(VaadinRequest request) {
        employeeGrid.setSizeFull();
        HorizontalLayout layout = new HorizontalLayout();
        VerticalLayout verticalLayout = new VerticalLayout();
        layout.addComponents(generateButton,addButton,hireButton,assignButton);
        Label mainLabel = new Label("Company Builder");
        mainLabel.setWidth("500px");
        verticalLayout.addComponents(mainLabel,layout);
        verticalLayout.addComponent(employeeGrid);
        setContent(verticalLayout);
        generateButton.addClickListener(event -> {
            GenerateCompanyWindow window = new GenerateCompanyWindow(this);
            addWindow(window);

        });
        addButton.addClickListener(event -> {
            AddEmployeeWindow window = new AddEmployeeWindow(this);
            addWindow(window);
        });

    }

}