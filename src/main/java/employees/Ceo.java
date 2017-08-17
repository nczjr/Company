import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ceo extends TeamManager {

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

    public Ceo(Builder builder){
        super(builder);
    }

    public ArrayList<Employee> getEmployeesArray() {
        return super.getEmployeesArray();
    }

    public void report(){
        Collections.sort(super.getEmployeesArray(),
                employeeComparator(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o2.getName().compareTo(o1.getName());
                    }
                }, new Comparator<Employee>() {
                    public int compare(Employee o1, Employee o2) {
                        return o2.getRole().compareTo(o1.getRole());
                    }
                }, new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o1.getReport().getHoursWorked()-o2.getReport().getHoursWorked();
                    }
                }));
        for (Employee e: getEmployeesArray())
            System.out.println("I'm printing employees and their reports" + e.getName() + e.getReport().toString());

    }

    public static class Builder extends TeamManager.Builder {


        public Builder(String name, int maxNumEmployees){
            super(name,maxNumEmployees);
            super.role(RoleType.CEO);
        }


        public Ceo build() {
            return new Ceo(this);
        }

    }
}
