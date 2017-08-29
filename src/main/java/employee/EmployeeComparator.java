package employee;

import java.util.Comparator;


public class EmployeeComparator  {
    public EmployeeComparator(){
    }

    public Comparator<Employee> getComparator(){
        return new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int result = compareName(o1,o2);
                if (result != 0) return result;
                else {
                    int res = compareRole(o1, o2);
                    if (res != 0) return res;
                    else return compareReport(o1,o2);
                }
            }
        };
    }
    private int compareName(Employee o1,Employee o2){
        return o1.getName().compareTo(o2.getName());
    }

    private int compareRole(Employee o1, Employee o2){
        return o1.getRole().compareTo(o2.getRole());
    }

    private int compareReport(Employee o1, Employee o2){
        return o1.getReport().getHoursWorked()-o2.getReport().getHoursWorked();
    }


}


