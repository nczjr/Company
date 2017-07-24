
public class App  {
    public static void main(String[] args){
        AbstractEmployee dev1 = new Empl( "employe1",RoleType.developer);
        AbstractEmployee test1 = new Empl( "employe2",RoleType.CEO);
        AbstractEmployee test2 = new Empl( "employe3",RoleType.contributor);
        AbstractEmployee test3 = new Empl( "employe4",RoleType.tester);
        AbstractEmployee test4 = new Empl( "employe5",RoleType.teamleader);
        AbstractEmployee test5 = new Empl( "employe6",RoleType.developer);
        TeamManager teamManager = new TeamManager("teammaneger",RoleType.teamleader, 5);
        teamManager.hire(dev1);
        teamManager.hire(test1);
        Task t1 = new Task(5, "something1");
        teamManager.assign(t1);
        Task t2 = new Task(10, "something2");
        Task t3 = new Task(10, "something3");
        Task t4 = new Task(10, "something4");
        Task t5 = new Task(10, "something5");
        Task t6 = new Task(10, "something6");
        System.out.println(test1.reportWork());
        teamManager.assign(t2);
        teamManager.assign(t2);
        teamManager.hire(test2);
        teamManager.hire(test3);
        teamManager.hire(test4);
        teamManager.hire(test5);
        teamManager.fire(test2);
        teamManager.hire(test5);
        teamManager.assign(t3);
        teamManager.assign(t4);
        teamManager.assign(t5);
        System.out.println(teamManager.reportWork());
        System.out.println("First worker");
        System.out.println(test1.reportWork());

        System.out.println("econd worker");
        System.out.println(test2.reportWork());

        System.out.println("Third worker");
        System.out.println(test3.reportWork());

        System.out.println("Forth worker");
        System.out.println(test4.reportWork());

        System.out.println("Fifth worker");
        System.out.println(test5.reportWork());



    }
}
