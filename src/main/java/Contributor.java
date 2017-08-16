
public class Contributor extends AbstractEmployee{
//    public Contributor(String name){
//        super(name,RoleType.CONTRIBUTOR);
//    }

    public Contributor(Builder builder){
        super(builder);
    }
    public void assign(Task... task){
        for (Task t: task) {
            t.perform();
            this.getReport().add(t);
        }
    }

    public static class Builder extends AbstractEmployee.Builder {

        public Builder(){

        }

        public Builder(String name){
            super(name,RoleType.CONTRIBUTOR);
        }

        public Contributor build() {
            return new Contributor(this);
        }

    }
}
