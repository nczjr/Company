import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private static ArrayList<TeamManager> managers;
    private static int numberOfManagers;

    public static void generate(int numberOfManag){
        numberOfManagers = numberOfManag;
        Random random = new Random();
        managers = new ArrayList<>(numberOfManagers);
        int size = random.nextInt(5);
        if (size >=1 ) {
            //I know casting is bad but what else could I do?
            managers.add(0, (TeamManager) EmployeeFactory.getEmployee("ceo"));
            for (int i = 1; i < numberOfManagers; i++) {
                managers.add((TeamManager) EmployeeFactory.getEmployee("teamManager"));
            }
            generateStructure();
            for (int i = 0; i < numberOfManagers; i++)
                managers.get(i).hire(generateEmployees(size));
            for (int i = 0; i < numberOfManagers; i++)
                managers.get(i).assign(generateTasks(size));
        } else generateEmployees(numberOfManag);

    }

    public static void generateReports(){
        for (TeamManager teamManager: managers) {
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
            employees.add(i,EmployeeFactory.getEmployee("developer"));
            if ((i+1) < size) {
                i++;
                employees.add(i,EmployeeFactory.getEmployee("tester"));
            }
        }
        return employees;
    }

    private static Task[] generateTasks(int size){
        Task[] tasks = new Task[size+4];
        for (int i = 0; i < tasks.length; i++){
            String taskName = "Task number " + (i+1);
            tasks[i] = (new Task((i+2),taskName));
        }
        return tasks;
    }
}
