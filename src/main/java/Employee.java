import java.util.ArrayList;

public interface Employee {
    RoleType getRole();
    String getName();
    void assign(Task... task);
    Report reportWork();
    Report getReport();
}
