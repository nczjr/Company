public abstract class AbstractEmployee implements Employee {
    private final String name;
    private RoleType role;
    private Report report;

    public AbstractEmployee(String name, RoleType role){
        this.name = name;
        this.role = role;
        report = new Report();
    }

    @Override
    public RoleType getRole() {
        return role;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void assign(Task... task){
        for (Task t: task) {
            t.perform();
            report.add(t);
        }
    }

    @Override
    public Report getReport() {
        return report;
    }

    public Report reportWork(){
        return this.report;
    }

    public String toString(){
        return ("Role: " + role.name() + " Name: " + name);
    }



}
