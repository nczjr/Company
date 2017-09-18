package employee;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HireStrategy {
    static Predicate<Employee> allEmployees(){
        return employee -> !employee.getName().isEmpty();
    }
    static Predicate<Employee> isWoman(){
        return employee -> employee.getSex().name() != null && employee.getSex().name().equalsIgnoreCase("female");
    }
    static Predicate<Employee> isMan(){
        return employee -> employee.getSex().name() != null && employee.getSex().name().equalsIgnoreCase("male");
    }
    static Predicate<Employee> isFromAGH(){
        return employee -> employee.getUniversity() != null && employee.getUniversity().equalsIgnoreCase("agh");
    }

    static Predicate<Employee> isFromPoland(){
        return employee -> employee.getCountry() != null && employee.getCountry().equalsIgnoreCase("poland");
    }

    static Predicate<Employee> hasGmailAccount(){
        return employee -> employee.getEmail() != null && employee.getEmail().endsWith("@gmail.com");
    }

    public static ArrayList<Employee> filterEmployees(ArrayList<Employee> employees, Predicate<Employee> predicate){
        return employees.stream()
                        .filter(predicate)
                        .collect(Collectors.toCollection(ArrayList<Employee>::new));
    }

    public static Predicate<Employee> getByName(String s){
        switch (s){
            case "only employees with gmail account": return hasGmailAccount();
            case "only employees from AGH": return isFromAGH();
            case "only employees from Poland": return isFromPoland();
            case "only women": return isWoman();
            case "only men": return isMan();
            default: return allEmployees();
        }
    }
}
