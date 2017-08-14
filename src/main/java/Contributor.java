
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

    public static class Builder<Builder> extends AbstractEmployee.Builder<Builder> {

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public Builder role(RoleType role) {
            return super.role(RoleType.CONTRIBUTOR);
        }

        public Contributor build() {
            return new Contributor(this);
        }

    }
}
