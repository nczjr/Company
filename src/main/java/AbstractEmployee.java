import javax.management.relation.Role;

public abstract class AbstractEmployee implements Employee {
    private String name;
    private RoleType role;
    private Report report;

    public AbstractEmployee(String name, RoleType role){
        this.name = name;
        this.role = role;
        report = new Report();
    }

    public RoleType getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void assign(TaskInterface task){
        report.add(task);
        task.perform();
    }

    public Report reportWork(){
        return this.report;
    }

    public String toString(){
        return ("Role: " + role.name() + " Name: " + name);
    }



}
