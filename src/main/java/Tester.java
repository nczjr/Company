
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

    public static class Builder<Builder> extends AbstractEmployee.Builder<Builder> {

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public Builder role(RoleType role) {
            return super.role(RoleType.TESTER);
        }

        public Tester build() {
            return new Tester(this);
        }

    }
}
