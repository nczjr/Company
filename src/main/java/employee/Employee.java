package employee;

import javafx.beans.property.SimpleStringProperty;
import task.Report;
import task.Task;

public interface Employee {
    Role getRole();
    String getName();
    void assign(Task... task);
    Report reportWork();
    Report getReport();
    String getUniversity();
    Sex getSex();
    String getCountry();
    String getTelephoneNumber();
    String getEmail();


}
