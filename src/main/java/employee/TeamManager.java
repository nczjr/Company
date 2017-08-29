package employee;

import task.Report;
import task.Task;

import java.util.ArrayList;
import java.util.function.Predicate;

import static java.util.Collections.min;
import static java.util.Collections.sort;
import static java.util.Comparator.comparing;


public class TeamManager extends AbstractEmployee implements Manager {

    private final ArrayList<Employee> employeesArray;
    private final Report report;
    private final int maxNumEmployes;
    private Predicate<Employee> hireStrategy;
    private ArrayList<Report> reports;


    public TeamManager(Builder builder){
        super(builder);
        this.employeesArray = new ArrayList<Employee>(builder.maxNumEmployes);
        this.report = new Report();
        this.maxNumEmployes = builder.maxNumEmployes;
        this.hireStrategy = builder.hireStrategy;

    }

    public ArrayList<Employee> getEmployeesArray() {
        return employeesArray;
    }

    @Override
    public void fire(Employee employee) {
        employeesArray.remove(employee);
    }

    @Override
    public void hire(ArrayList<Employee> employees) {
        for (Employee e: employees) {
            hire(e);
        }
    }

    @Override
    public void hire(Employee employee) {
        if (!employeesArray.contains(employee) && canHire(employee)) {
                employeesArray.add(employee);
        }
    }

    public void assign(Task... task) {
        for (Task t: task) {
            if (!employeesArray.isEmpty()) {
                System.out.println(this.getName() + " has total of " + this.reportWork().getHoursWorked() + " hours of work");
                for (Employee e: this.getEmployeesArray()) {
                    System.out.println(e.getName() + " has " + e.reportWork().getHoursWorked() + " hours of work");
                }
                AbstractEmployee employeeToBeSigned = (AbstractEmployee) min(this.employeesArray, comparing(employee -> employee.getReport().getHoursWorked()));
                employeeToBeSigned.assign(t);
                System.out.println(employeeToBeSigned.getName() + " got assigned to task: " + t);
                report.add(t);
            }
        }
    }

    @Override
    public Report reportWork() {
//        if (getRole().compareTo(Role.CEO) == 0){
//            return getReports();
//        }else{
//            return this.report;
//        }
    return report;
    }


    @Override
    public boolean canHire(Employee employee) {
        return employeesArray.size()<maxNumEmployes && hireStrategy.test(employee);
    }


    public ArrayList<Report> getReports(){
        sort(getEmployeesArray(),
                new EmployeeComparator().getComparator());
        for (Employee e: getEmployeesArray())
            if (e.reportWork().getHoursWorked() != 0){

                reports.add(e.reportWork());
            }
        return reports;
    }


    @Override
    public String toString() {
        return "employee.TeamManager " +
                "employees: " + employeesArray +
                ", report=" + report +
                super.toString();
    }

    public static class Builder extends AbstractEmployee.Builder<Builder>{

        private int maxNumEmployes;
        private Predicate<Employee> hireStrategy;


        public Builder(String name, Role role, int maxNumEmployes){
            super(name,role);
            this.maxNumEmployes = maxNumEmployes;
            this.hireStrategy = HireStrategy.allEmployees();
        }

        public Builder maxNumEmployes(int maxNumEmployes){
            this.maxNumEmployes = maxNumEmployes;
            return this;
        }

        public Builder hireStrategy(Predicate<Employee> hireStrategy){
            this.hireStrategy = hireStrategy;
            return this;
        }

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public TeamManager build() {
            return new TeamManager(this);
        }
    }


}

