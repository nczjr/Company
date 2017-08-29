package employee;

import task.Task;

public class Developer extends AbstractEmployee {

    public Developer(Builder builder) {
        super(builder);
    }
    public void assign(Task... task){
        for (Task t: task) {
            t.perform();
            this.getReport().add(t);
        }
    }


    public static class Builder extends AbstractEmployee.Builder<Builder>{

        private Builder(){
            super();
            role(Role.DEVELOPER);
        }

        public Builder(String name, Role role){
            super(name, role);
        }

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public Developer build() {
            return new Developer(this);
        }

    }
}