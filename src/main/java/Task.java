
public class Task {
    private int unitsOfWork;
    private String taskName;

    public Task(){
        unitsOfWork = 0;
    }

    public Task(int unitsOfWork, String taskName){
        this.unitsOfWork = unitsOfWork;
        this.taskName = taskName;
    }

    public void perform(){
        System.out.println(this);
    }


    public int getUnitsOfWork() {
        return unitsOfWork;
    }

    public void setUnitsOfWork(int unitsOfWork) {
        this.unitsOfWork = unitsOfWork;
    }

    @Override
    public String toString() {
        return  taskName + ": " +
                unitsOfWork + "unitsOfWork" +
                " \n";
    }
}
