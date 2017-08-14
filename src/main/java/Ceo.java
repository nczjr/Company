
public class Ceo extends TeamManager {

//    public Ceo(String name, int size){
//        super(name,RoleType.CEO,size);
//    }

    public Ceo(Builder builder){
        super(builder);
    }

    public static class Builder<Builder> extends AbstractEmployee.Builder<Builder> {


        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public Builder role(RoleType role) {
            return super.role(RoleType.CEO);
        }


        public Ceo build() {
            return new Ceo(this);
        }

    }
}
