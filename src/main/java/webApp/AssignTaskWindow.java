package webApp;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import employee.Employee;
import employee.TeamManager;
import generator.Generator;
import javafx.scene.input.KeyCode;
import task.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

class AssignTaskWindow extends Window{
    private final TextField taskName = new TextField("Task name");
    private final TextField workUnits = new TextField("Units of work");
    private final Button closeBtn = new Button("Close");
    private final Button assign = new Button("Assign");
    private final HorizontalLayout buttons = new HorizontalLayout(assign,closeBtn);
    private final VerticalLayout layout = new VerticalLayout(taskName, workUnits,buttons);
    private MyUI myUI;

    AssignTaskWindow(MyUI myUI){
        super("Assign task");
        this.myUI = myUI;
        assign.setEnabled(false);
        assign.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setContent(layout);
        center();
        setValueChangeListeners();
        setOnClickListeners();

    }

    private void setOnClickListeners() {
        assign.addClickListener(event -> {
            if (isEnabled()) {
                myUI.getSelectedEmployee().assign(new Task(Integer.parseInt(workUnits.getValue()) ,taskName.getValue()));
                close();
                myUI.getGrid().getDataProvider().refreshAll();
            }
        });
        closeBtn.addClickListener(event -> close());
    }

    private void setValueChangeListeners() {
        taskName.addValueChangeListener(event -> checkValidation());
        workUnits.addValueChangeListener(event -> checkValidation());
    }


    private boolean isValidNumber() {
        if (!(workUnits.getValue().matches("[0-9]+") || workUnits.getValue().isEmpty())) {
            Notification.show("Units of work field must be filled with an integer and cannot be empty");
            return false;
        } else return true;
    }

    private boolean isValidName() {
        if (taskName.isEmpty() || !taskName.getValue().matches("^[A-ZĄąĆćĘęŁłŃńÓóŚśŹźŻża-z', ]+$")){
            Notification.show("Task name must be a string and cannot be empty");
            return false;
        } else return true;
    }

    private void checkValidation(){
        assign.setEnabled(isValidNumber() && isValidName());
    }
}
