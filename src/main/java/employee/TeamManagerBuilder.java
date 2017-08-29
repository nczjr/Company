//package employee;
//
//import java.util.function.Predicate;
//
//public class TeamManagerBuilder extends AbstractEmployee.Builder<Builder>{
//
//    private int maxNumEmployes;
//    private Predicate<Employee> hireStrategy;
//
//
//    public TeamManagerBuilder(String name, Role role, int maxNumEmployes){
//        super(name,role);
//        this.maxNumEmployes = maxNumEmployes;
//        this.hireStrategy = HireStrategy.allEmployees();
//    }
//
//    public TeamManagerBuilder maxNumEmployes(int maxNumEmployes){
//        this.maxNumEmployes = maxNumEmployes;
//        return this;
//    }
//
//    public TeamManagerBuilder hireStrategy(Predicate<Employee> hireStrategy){
//        this.hireStrategy = hireStrategy;
//        return this;
//    }
//
//    @Override
//    public TeamManagerBuilder getThis() {
//        return this;
//    }
//
//    @Override
//    public TeamManager build() {
//        return new TeamManager(this);
//    }
//
//}
