import java.util.ArrayList;
import java.util.Random;


public class TeamManager extends AbstractEmployee implements Manager {

    private ArrayList<Employee> employeesArray;
    private Report report;
    private int maxNumEmployes;


    //Should I implement other contructors (like default constructor) even though I won't be using it???

    public TeamManager(String name, RoleType role, int size) {
        super(name, role);
        employeesArray = new ArrayList<Employee>(size);
        maxNumEmployes = size;
        report = new Report(this);
    }

    @Override
    public void fire(Employee employee) {
        if (employeesArray.size()<=0)
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
            int count = 0;
            for (Employee em: employee) {
                if (!employeesArray.contains(em)) {
                    employeesArray.add(em);
                    System.out.println(this.getName() + " hired " + em.getName());
                    count++;
                    if (count >= maxNumEmployes)
                        break;
                }else continue;
            }

        }
        else System.out.println("Too many employees, you have to fire someone first!");
    }

    public void assign(Task task) {
        report.add(task);
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




}

