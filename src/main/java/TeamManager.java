import java.util.ArrayList;
import java.util.Random;


public class TeamManager extends AbstractEmployee implements Manager {

    private ArrayList<Employee> employeesArray;
    private Report report;
    private int maxNumEmployes;

    public TeamManager(){
        super("",RoleType.teamleader);
        employeesArray = new ArrayList<Employee>(1);
        maxNumEmployes = 1;
    }

    public TeamManager(String name, RoleType role){
        super(name,role);
        employeesArray = new ArrayList<Employee>(1);
        maxNumEmployes = 1;
    }

    public TeamManager(String name, RoleType role, int size) {
        super(name, role);
        employeesArray = new ArrayList<Employee>(size);
        maxNumEmployes = size;
    }

    @Override
    public void fire(Employee employee) {
        if (howManyEmployees()<=0)
            System.out.println("You cannot fire anyone, you do not have any employees hired!");
        else {
            if (employeesArray.contains(employee)) {
                employeesArray.remove(employee);
            }
            else
                System.out.println("You cannot fire this employee because he is not yours!");
        }
    }

    @Override
    public void hire(Employee employee) {
        if (canHire()) {
            employeesArray.add(employee);
            System.out.println(employee.getName() + " hired!");
        }
        else System.out.println("Too many employees, you have to fire someone first!");
    }

    @Override
    public void assign(TaskInterface task) {
        if (!employeesArray.isEmpty()) {
            Random generator = new Random();
            int i = generator.nextInt(this.howManyEmployees());
            Employee empl = employeesArray.get(i);
            empl.assign(task);
        }
    }

    @Override
    public Report reportWork() {
        return this.report;
    }

    @Override
    public boolean canHire() {
        int count = howManyEmployees();
        if (count>=maxNumEmployes)
            return false;
        else return true;
    }

    private int howManyEmployees(){
        int count = 0;
        for (Employee employee: employeesArray) {
            if (!employeesArray.isEmpty() && (employeesArray.get(count)!=null))
                count++;
        }
        return count;
    }



}

