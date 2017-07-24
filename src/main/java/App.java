
public class App  {
    public static void main(String[] args){
        AbstractEmployee dev1 = new Empl( "Dev1",RoleType.developer);
        AbstractEmployee contributor = new Empl( "Contributor",RoleType.contributor);
        AbstractEmployee tester = new Empl( "Tester",RoleType.tester);
        AbstractEmployee teamleader = new Empl( "Team leader",RoleType.teamleader);
        AbstractEmployee dev2 = new Empl( "Dev2",RoleType.developer);
        TeamManager teamManager = new TeamManager("Team manager",RoleType.teamleader, 5);
        TeamManager ceo = new TeamManager("Ceo",RoleType.CEO,3);
        ceo.hire(dev2,contributor,teamleader,teamManager);
        teamManager.hire(dev1,tester);
        Task t1 = new Task(5, "something1");
        Task t2 = new Task(10, "something2");
        Task t3 = new Task(10, "something3");
        Task t4 = new Task(10, "something4");
        Task t5 = new Task(10, "something5");
        Task t6 = new Task(10, "something6");
        dev1.assign(t1);
        teamManager.assign(t2);
        teamManager.hire(teamleader);
        teamManager.hire(dev2);
        teamManager.hire(dev2);
        ceo.assign(t2);
        ceo.assign(t5);
        ceo.assign(t3);
        teamManager.assign(t3);
        teamManager.assign(t4);
        teamManager.assign(t5);
        System.out.println(teamManager.reportWork());

        System.out.println(ceo.reportWork());


        System.out.println(contributor.reportWork());

        System.out.println(tester.reportWork());


        System.out.println(teamleader.reportWork());


        System.out.println(dev2.reportWork());



    }
}
