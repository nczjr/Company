import java.util.ArrayList;
import java.util.Random;


public class TeamManager extends AbstractEmployee implements Manager {

    private ArrayList<Employee> employeesArray;
    private Report report;
    private int maxNumEmployes;


    public TeamManager(String name, RoleType role, int size) {
        super(name, role);
        employeesArray = new ArrayList<Employee>(size);
        maxNumEmployes = size;
        report = new Report();
    }

    @Override
    public void fire(Employee employee) {
        if (employeesArray.isEmpty())
            System.out.println("You cannot fire anyone, you do not have any employees hired!");
        else {
            if (employeesArray.contains(employee)) {
                String name = employee.getName();
                employeesArray.remove(employee);
                System.out.println(name + " got fired!");
            }
            else
                System.out.println("You cannot fire this employee because he is not yours!");
        }
    }

    @Override
    public void hire(Employee... employee) {
        if (canHire()) {
            for(int i = 0; i < employee.length; i++){
                if (!employeesArray.contains(employee[i])) {
                    employeesArray.add(employee[i]);
                    System.out.println(this.getName() + " hired " + employee[i].getName());

                    if ((i+1)>= maxNumEmployes)
                        break;
                }else continue;
            }

        }
        else System.out.println("Too many employees, you have to fire someone first!");
    }

    public void assign(Task... task) {
        for (Task t: task) {
            AbstractEmployee employeeToBeSigned = (AbstractEmployee) getLessOverburnedWorker();
            System.out.println(this.getName() + " assigned " + employeeToBeSigned.getName() + " to the task" );
            employeeToBeSigned.assign(t);
            report.add(t);
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
        if (employeesArray.size()>=maxNumEmployes)
            return false;
        else return true;
    }

    @Override
    public Report getReport() {
        return report;
    }
}

