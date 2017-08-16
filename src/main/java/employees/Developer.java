
public class Developer extends AbstractEmployee {
//    public Developer(String name){
//        super(name,RoleType.DEVELOPER);
//    }

    public Developer(Builder builder) {
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
            role(RoleType.DEVELOPER);
        }

        public Builder(String name){
            super(name,RoleType.DEVELOPER);
        }

        public Developer build() {
            return new Developer(this);
        }

    }
}