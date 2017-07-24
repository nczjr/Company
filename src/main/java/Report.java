import java.util.ArrayList;
import java.util.List;


public class Report {
    private Employee whoseReport;
    private int hoursWorked;
    private List<Task> tasks;

    public void add(Task task){
        tasks.add(task);
    }


    public Report(Employee employee){
        whoseReport = employee;
        hoursWorked = 0;
        tasks = new ArrayList<Task>(1);
    }

    public String toString(){
        if (tasks.isEmpty()){
            String s = whoseReport.getName() + " performed 0 tasks \n";
            return s;
        }else{
            String s = ("All tasks performed by " + whoseReport.getName() + " are:  \n" );
            for (Task task: tasks) {
                s += task + "\n";
            }
            return s;
        }

    }


}
