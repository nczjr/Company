
public class Tester extends AbstractEmployee {
//    public Tester(String name){
//        super(name,RoleType.TESTER);
//    }
    public Tester(Builder builder){
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
            role(RoleType.TESTER);
        }
        public Tester build() {
            return new Tester(this);
        }

    }
}
