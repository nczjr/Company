package task;

import java.util.ArrayList;
import java.util.List;


public class Report {
    private int hoursWorked;
    private List<Task> tasks;


    public Report(){
        hoursWorked = 0;
        tasks = new ArrayList<>(1);
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
        if (tasks.isEmpty())
            return " 0 tasks performed \n";
        else{
            StringBuilder stringBuilder = new StringBuilder();
            for (Task task: tasks) {
                stringBuilder.append(task).append("\n");
            }
            return stringBuilder.toString();
        }

    }


}
