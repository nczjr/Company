
public class App  {
    public static void main(String[] args){
//        Generator.generate(3);
//        Generator.generateReports();

        Developer.Builder devBuild = new Developer.Builder("A");
        devBuild.email("natka@gmail.com")
                .country("POLAND")
                .telephoneNumber("123456778")
                .university("aGH ");
        Developer developer = devBuild.build();
        Developer.Builder devBuild1 = new Developer.Builder("B");
        devBuild.email("natka@gmail.com")
                .country("POLAND")
                .telephoneNumber("123456778")
                .university("aGH ");
        Developer developer1 = devBuild1.build();
        Developer.Builder devBuild2 = new Developer.Builder("C");
        devBuild.email("natka@gmail.com")
                .country("POLAND")
                .telephoneNumber("123456778")
                .university("aGH ");
        Developer developer2 = devBuild2.build();
        TeamManager.Builder mng = new TeamManager.Builder("Antek",5);

        TeamManager manager = mng.build();
        System.out.println(developer );
        System.out.println(manager);

        manager.hire(developer);
        manager.hire(developer1);
        manager.hire(developer2);
        Task t1 = new Task(4,"kodzeni");
        Task t2 = new Task(5,"kod");
        Task t3 = new Task(4,"zenie");
        Task t4 = new Task(4,"kodzenie");
        Task t5 = new Task(4,"kodzenie");
        manager.assign(t1,t2,t3,t4,t5);
        System.out.println(manager);
    }
}
