//package employee;
//
//public class DeveloperBuilder extends Builder<Builder>{
//
//    private DeveloperBuilder(){
//        super();
//        role(Role.DEVELOPER);
//    }
//
//    public DeveloperBuilder(String name, Role role){
//        super(name, role);
//    }
//
//    @Override
//    public DeveloperBuilder getThis() {
//        return this;
//    }
//
//    @Override
//    public Developer build() {
//        return new Developer(this);
//    }
//
//}
