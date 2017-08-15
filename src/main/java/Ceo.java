
public class Ceo extends TeamManager {

//    public Ceo(String name, int size){
//        super(name,RoleType.CEO,size);
//    }

    public Ceo(Builder builder){
        super(builder);
    }

    public static class Builder extends TeamManager.Builder {


        public Builder(){
            role(RoleType.CEO);
        }


        public Ceo build() {
            return new Ceo(this);
        }

    }
}
