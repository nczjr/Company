import java.util.ArrayList;
import java.util.List;


public class Report implements TaskInterface{
    private int hoursWorked;
    private List<TaskInterface> tasks;

    public void add(TaskInterface task){
        tasks.add(task);
    }

    public void perform(){
        for (TaskInterface task: tasks) {
            task.perform();
        }

    }

    public Report(){
        hoursWorked = 0;
        tasks = new ArrayList<TaskInterface>(1);
    }

    public String toString(){
        String s = ("All tasks took: \n" );
        for (TaskInterface task: tasks) {
             s += task + "\n" ;
        }
        return s;
    }


}
