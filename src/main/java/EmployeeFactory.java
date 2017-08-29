import employee.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EmployeeFactory {
    private static ArrayList<String> countries = new ArrayList<>(Arrays.asList("Poland", "Germany", "Italy", "France", "Netherlands"));
    private static ArrayList<String> univeristies = new ArrayList<>(Arrays.asList("AGH", "UJ", "UEK", "WAT", "PK"));
    private static ArrayList<String> emailDomain = new ArrayList<>(Arrays.asList("@gmail.com", "@onet.pl", "@wp.pl", "@interia.pl"));

    public static Employee getEmployee(String employeeType){
        switch (employeeType){
            case "developer": return new Developer.Builder(generateString(), Role.DEVELOPER)
                                .university(getUniversity())
                                .sex(getSex())
                                .country(getCountry())
                                .telephoneNumber(getTelephoneNumber())
                                .email(getEmail())
                                .build();
            case "tester": return new Developer.Builder(generateString(),Role.TESTER)
                                            .university(getUniversity())
                                            .sex(getSex())
                                            .country(getCountry())
                                            .telephoneNumber(getTelephoneNumber())
                                            .email(getEmail())
                                            .build();
            case "teamManager": return new TeamManager.Builder(generateString(),Role.TEAM_LEADER,5)
                                            .university(getUniversity())
                                            .sex(getSex())
                                            .country(getCountry())
                                            .telephoneNumber(getTelephoneNumber())
                                            .email(getEmail())
                                            .build();
            case "ceo": return new TeamManager.Builder(generateString(),Role.CEO,5)
                                            .university(getUniversity())
                                            .sex(getSex())
                                            .country(getCountry())
                                            .telephoneNumber(getTelephoneNumber())
                                            .email(getEmail())
                                            .build();
            default: return new Developer.Builder(generateString(),Role.CONTRIBUTOR)
                                            .university(getUniversity())
                                            .sex(getSex())
                                            .country(getCountry())
                                            .telephoneNumber(getTelephoneNumber())
                                            .email(getEmail())
                                            .build();
        }
    }

    private static String generateString() {
        String characters = "abcdefghijklmnoprstuwxyz";
        String result = "";
        Random random = new Random();
        for(int i = 0; i < 7; i++)
            result += characters.charAt(random.nextInt(characters.length()));
        return result;
    }

    private static String getUniversity(){
        Random random = new Random();
        return univeristies.get(random.nextInt(univeristies.size()));
    }

    private static Sex getSex(){
        Random random = new Random();
        if (random.nextInt() % 2 == 0)
            return Sex.FEMALE;
        else return Sex.MALE;
    }

    private static String getCountry(){
        Random random = new Random();
        return countries.get(random.nextInt(countries.size()));
    }

    private static String getTelephoneNumber(){
        Random random = new Random();
        int num1 = (random.nextInt(7) + 1) * 100 + (random.nextInt(8) * 10) + random.nextInt(8);
        int num2 = random.nextInt(743);
        int num3 = random.nextInt(10000);
        return String.format("%d%d%d", num1, num2, num3);
    }

    private static String getEmail(){
        Random random = new Random();
        return generateString()+ emailDomain.get(random.nextInt(emailDomain.size()));
    }
}
