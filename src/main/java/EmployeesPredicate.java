import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeesPredicate {
    public static Predicate<Employee> allEmployees(){
        return employee -> !employee.getName().isEmpty();
    }
    public static Predicate<Employee> isWoman(){
        return employee -> employee.getSex().name().equalsIgnoreCase("female");
    }
    public static Predicate<Employee> isMan(){
        return employee -> employee.getSex().name().equalsIgnoreCase("male");
    }
    public static Predicate<Employee> isFromAGH(){
        return employee -> employee.getUniversity().equalsIgnoreCase("agh");
    }

    public static Predicate<Employee> isFromPoland(){
        return employee -> employee.getCountry().equalsIgnoreCase("poland");
    }

    public static Predicate<Employee> hasGmailAccount(){
        return employee -> employee.getEmail().endsWith("@gmail.com");
    }

    public static ArrayList<Employee> filterEmployees(ArrayList<Employee> employees, Predicate<Employee> predicate){
        return employees.stream()
                        .filter(predicate)
                        .collect(Collectors.toCollection(ArrayList<Employee>::new));
    }
}
