package generator;

import employee.Employee;
import employee.Role;
import employee.TeamManager;
import task.Report;
import task.Task;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private static ArrayList<TeamManager> managers;
    private static int numberOfManagers;

    public static ArrayList<TeamManager> getManagers(){
        return managers;
    }

    public static void generate(int numberOfManag,int numberOfMaxEmployees){
        numberOfManagers = numberOfManag;
        Random random = new Random();
        managers = new ArrayList<>(numberOfManagers);
        int size = random.nextInt(numberOfMaxEmployees);
        if (size >=1 ) {
            managers.add(0, (TeamManager) EmployeeFactory.getEmployee("ceo"));
            System.out.println("Ceo : " + managers.get(0).getName());
            for (int i = 1; i < numberOfManagers; i++) {
                managers.add((TeamManager) EmployeeFactory.getEmployee("teamManager"));
                System.out.println(i + " manager: " + managers.get(i).getName());
            }
            generateStructure();
            for (int i = 0; i < numberOfManagers; i++)
            {
                managers.get(i).hire(generateEmployees(size));
                System.out.println(managers.get(i).getName() + " hires as follows ");
                for (Employee e : managers.get(i).getEmployeesArray())
                    System.out.println(e.getName() + " got hired");
            }
            //for (int i = 0; i < numberOfManagers; i++)
                managers.get(0).assign(generateTasks(size));
        } else generateEmployees(numberOfManag);

    }

    public static void generateReports(){
        for (TeamManager teamManager: managers) {
            if (teamManager.getRole() == Role.CEO) {
                System.out.println(teamManager.getName() + " subordinates reports: ");
                for (Report r : teamManager.getReports()) {
                    System.out.println(r);
                }
            }
            System.out.println(teamManager.getName() + "'s report");
            System.out.println(teamManager.reportWork());
        }
    }

    private static void generateStructure(){
        for (int i = 0; i < numberOfManagers; i++){
            if ((i + 1) < numberOfManagers)
                managers.get(i).hire(managers.get(i+1));
        }
    }

    private static ArrayList<Employee> generateEmployees(int size){
        ArrayList<Employee> employees = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            employees.add(i, EmployeeFactory.getEmployee("developer"));
            if ((i+1) < size) {
                i++;
                employees.add(i, EmployeeFactory.getEmployee("tester"));
            }
        }
        return employees;
    }

    private static Task[] generateTasks(int size){
        Task[] tasks = new Task[size+4];
        for (int i = 0; i < tasks.length; i++){
            String taskName = "task " + (i+1);
            tasks[i] = (new Task((i+2),taskName));
        }
        return tasks;
    }
}
