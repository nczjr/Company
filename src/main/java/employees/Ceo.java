
public class Ceo extends TeamManager {

    public Ceo(Builder builder){
        super(builder);
    }

    public static class Builder extends TeamManager.Builder {


        public Builder(String name, int maxNumEmployees){
            super(name,maxNumEmployees);
            super.role(RoleType.CEO);
        }


        public Ceo build() {
            return new Ceo(this);
        }

    }
}
