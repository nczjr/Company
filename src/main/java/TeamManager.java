import java.util.ArrayList;


public class TeamManager extends AbstractEmployee implements Manager {

    private final ArrayList<Employee> employeesArray;
    private final Report report;
    private int maxNumEmployes;


//    public TeamManager(String name, RoleType role, int size) {
//        super(name, role);
//        employeesArray = new ArrayList<Employee>(size);
//        maxNumEmployes = size;
////        report = new Report();
//    }

    public TeamManager(Builder builder){
        super(builder);
        this.employeesArray = new ArrayList<Employee>(builder.maxNumEmployes);
        this.report = new Report();
        this.maxNumEmployes = builder.maxNumEmployes;

    }

    @Override
    public void fire(Employee employee) {
        if (!employeesArray.contains(employee))
            System.out.println("You cannot fire anyone, you do not have such employee hired!");
        else {
            String name = employee.getName();
            employeesArray.remove(employee);
            System.out.println(name + " got fired!");

        }
    }

    @Override
    public void hire(ArrayList<Employee> employee) {
        if (canHire()) {
            for(int i = 0; i < employee.size(); i++){
                if (!employeesArray.contains(employee.get(i))) {
                    employeesArray.add(employee.get(i));
                    System.out.println(this.getName() + " hired " + employee.get(i).getName());
                    if ((i+1)>= maxNumEmployes)
                        break;
                }else continue;
            }

        }
        else System.out.println("Too many employees, you have to fire someone first!");
    }

    @Override
    public void hire(Employee employee) {
        if (canHire())
            employeesArray.add(employee);
        else
            System.out.println("Too many employees, you have to fire someone first!");
    }

    public void assign(Task... task) {
        for (Task t: task) {
            if (!employeesArray.isEmpty()) {
                AbstractEmployee employeeToBeSigned = (AbstractEmployee) getLessOverburnedWorker();
                System.out.println(this.getName() + " assigned " + employeeToBeSigned.getName() + " to the task" );
                employeeToBeSigned.assign(t);
                report.add(t);
            }
        }

    }

    public Employee getLessOverburnedWorker(){
        int hours = 0;
        Employee employeeWithMinHours = null;
        for(int i = 0; i < employeesArray.size(); i++){
              if (i == 0) {
                  employeeWithMinHours = employeesArray.get(i);
                  hours = employeeWithMinHours.getReport().getHoursWorked();
              }
              if (employeesArray.get(i).getReport().getHoursWorked()<hours){
                employeeWithMinHours = employeesArray.get(i);
                hours = employeeWithMinHours.getReport().getHoursWorked();
              }
        }
        return employeeWithMinHours;
    }

    @Override
    public Report reportWork() {
        return this.report;
    }

    @Override
    public boolean canHire() {
        return (employeesArray.size()>=maxNumEmployes);
    }

    @Override
    public Report getReport() {
        return report;
    }


    public static class Builder extends AbstractEmployee.Builder {

        private int maxNumEmployes;

        public Builder(){
            role(RoleType.TEAM_LEADER);
        }

        public Builder maxNumEmployes(int maxNumEmployes){
            this.maxNumEmployes = maxNumEmployes;
            return this;
        }

        @Override
        public TeamManager build() {
            return new TeamManager(this);
        }

    }
}

