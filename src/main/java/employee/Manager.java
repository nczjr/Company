package employee;

import employee.Employee;

import java.util.ArrayList;

public interface Manager extends Employee {
    void hire(ArrayList<Employee> employee);
    void hire(Employee employee);
    void fire(Employee employee);
    boolean canHire(Employee employee);

}
