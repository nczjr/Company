
public class App  {
    public static void main(String[] args){
//        Generator.generate(3);
//        Generator.generateReports();

        Developer.Builder devBuild = new Developer.Builder();
        devBuild.name("Aa")
                .email("")
                .country("POLAND")
                .telephoneNumber("123456778")
                .university("aGH ");
        Developer developer = devBuild.build();
        System.out.println(developer);
    }
}
