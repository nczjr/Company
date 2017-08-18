import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

import static java.util.Collections.*;
import static java.util.Comparator.*;


public class TeamManager extends AbstractEmployee implements Manager {

    private final ArrayList<Employee> employeesArray;
    private final Report report;
    private final int maxNumEmployes;
    private Predicate<Employee> employeesPredicate;
    private ArrayList<Report> reports;


    public TeamManager(Builder builder){
        super(builder);
        this.employeesArray = new ArrayList<Employee>(builder.maxNumEmployes);
        this.report = new Report();
        this.maxNumEmployes = builder.maxNumEmployes;
        this.employeesPredicate = builder.employeesPredicate;

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
                AbstractEmployee employeeToBeSigned = (AbstractEmployee) min(employeesArray, comparing(employee -> employee.getReport().getHoursWorked()));
                employeeToBeSigned.assign(t);
                report.add(t);
            }
        }
    }

    @Override
    public Report reportWork() {
        if (getRole().compareTo(Role.CEO) == 0){
            return report();
        }else{
            return this.report;
        }
    }

    @Override
    public boolean canHire() {
        return (employeesArray.size()<maxNumEmployes);
    }

    @Override
    public boolean canHire(Employee employee) {
        return canHire() && employeesPredicate.test(employee);
    }


    public Report report() {
            sort(getEmployeesArray(),
                    employeeComparator(new Comparator<Employee>() {
                        @Override
                        public int compare(Employee o1, Employee o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    }, new Comparator<Employee>() {
                        public int compare(Employee o1, Employee o2) {
                            return o1.getRole().compareTo(o2.getRole());
                        }
                    }, new Comparator<Employee>() {
                        @Override
                        public int compare(Employee o1, Employee o2) {
                            return o1.getReport().getHoursWorked()-o2.getReport().getHoursWorked();
                        }
                    }));
        for (Employee e: getEmployeesArray())
                reports.add(e.getReport());

        return report;
    }

    @Override
    public String toString() {
        return "TeamManager " +
                "employees: " + employeesArray +
                ", report=" + report +
                super.toString();
    }

    public static Comparator<Employee>  employeeComparator(Comparator<? super Employee> comp1,Comparator<? super Employee> comp2,Comparator<? super Employee> comp3) {
        return new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int result = comp1.compare(o1,o2);
                if (result != 0) return result;
                else {
                    int res = comp2.compare(o1, o2);
                    if (res != 0) return res;
                    else return comp3.compare(o1,o2);
                }
            }
        };
    }

    public static class Builder extends AbstractEmployee.Builder<Builder>{

        private int maxNumEmployes;
        private Predicate<Employee> employeesPredicate;


        public Builder(String name, Role role,int maxNumEmployes){
            super(name,role);
            this.maxNumEmployes = maxNumEmployes;
            this.employeesPredicate = EmployeesPredicate.allEmployees();
        }

        public Builder maxNumEmployes(int maxNumEmployes){
            this.maxNumEmployes = maxNumEmployes;
            return this;
        }

        public Builder employeesPredicate(Predicate<Employee> employeesPredicate){
            this.employeesPredicate = employeesPredicate;
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

