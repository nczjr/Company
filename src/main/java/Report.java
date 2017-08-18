import java.util.ArrayList;
import java.util.List;


public class Report {
    private int hoursWorked;
    private List<Task> tasks;


    public Report(){
        hoursWorked = 0;
        tasks = new ArrayList<Task>(1);
    }


    public void add(Task task){
        tasks.add(task);
    }

    private void countHours(){
        this.hoursWorked = 0;
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                this.hoursWorked += task.getUnitsOfWork();
            }
        }
    }

    public int getHoursWorked(){
        countHours();
        return this.hoursWorked;
    }

    public String toString(){
        if (tasks.isEmpty()){
            String s = " 0 tasks performed \n";
            return s;
        }else{
            String s = ("All tasks performed are:  \n" );
            for (Task task: tasks) {
                s += task + "\n";
            }
            return s;
        }

    }


}
