//import java.util.ArrayList;
//import java.util.Random;
//
//public class Generator {
//    private static ArrayList<TeamManager> managers;
//    private static int numberOfManagers;
//
//    public static void generate(int numberOfManag){
//        numberOfManagers = numberOfManag;
//        Random random = new Random();
//        managers = new ArrayList<>(numberOfManagers);
//        int size = random.nextInt(5);
//        System.out.println(size);
//        if (size > 0) {
//            for (int i = 0; i < numberOfManagers; i++) {
//                String name = "Manager " + (i + 1);
//                managers.add(new TeamManager(name, RoleType.TEAM_LEADER, size));
//            }
//            generateStructure();
//            for (int i = 0; i < numberOfManagers; i++)
//                managers.get(i).hire(generateEmployees(size));
//            for (int i = 0; i < numberOfManagers; i++)
//                managers.get(i).assign(generateTasks(size));
//        }
//        else generate(numberOfManagers);
//
//    }
//
//    public static void generateReports(){
//        for (TeamManager teamManager: managers) {
//            System.out.println(teamManager.getName() + "'s report");
//            System.out.println(teamManager.reportWork());
//        }
//    }
//
//    private static void generateStructure(){
//        for (int i = 0; i < numberOfManagers; i++){
//            if ((i + 1) < numberOfManagers)
//                managers.get(i).hire(managers.get(i+1));
//        }
//    }
//
//    private static ArrayList<Employee> generateEmployees(int size){
//        ArrayList<Employee> employees = new ArrayList<>(size);
//        for (int i = 0; i < size; i++){
//            String name = "Worker " + (i+1);
//            employees.add(i,new Contributor(name));
//            if ((i+1) < size) {
//                i++;
//                String nam = "Worker " + (i + 1);
//                employees.add(i,new Developer(nam));
//            }
//        }
//        return employees;
//    }
//
//    private static Task[] generateTasks(int size){
//        Task[] tasks = new Task[size+4];
//        for (int i = 0; i < tasks.length; i++){
//            String taskName = "Task number " + (i+1);
//            tasks[i] = (new Task((i+2),taskName));
//        }
//        return tasks;
//    }
//}
