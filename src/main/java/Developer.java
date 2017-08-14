
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


    public static class Builder<Builder> extends AbstractEmployee.Builder<Builder> {

        public Builder(){

        }

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public Builder role(RoleType role) {
            return super.role(RoleType.DEVELOPER);
        }

        public Developer build() {
            return new Developer(this);
        }

    }
}