package generator;

import employee.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class EmployeeFactory {
    private static ArrayList<String> names = new ArrayList<>(Arrays.asList("Piotr","Anna","Lukasz", "Paulina", "Agnieszka", "Oliwia", "Mateusz", "Grzegorz", "Joanna", "Monika","Wojtek","Aleksandra"));
    private static ArrayList<String> countries = new ArrayList<>(Arrays.asList("Poland", "Germany", "Italy", "France", "Netherlands"));
    private static ArrayList<String> univeristies = new ArrayList<>(Arrays.asList("AGH", "UJ", "UEK", "WAT", "PK"));
    private static ArrayList<String> emailDomain = new ArrayList<>(Arrays.asList("@gmail.com", "@onet.pl", "@wp.pl", "@interia.pl"));

    static Employee getEmployee(String employeeType){
        switch (employeeType){
            case "developer": {
                String name = generateString();
                return new Developer.Builder(name, Role.DEVELOPER)
                        .university(getUniversity())
                        .sex(getSex())
                        .country(getCountry())
                        .telephoneNumber(getTelephoneNumber())
                        .email(getEmail(name))
                        .build();
            }
            case "tester":{
                String name = generateString();
                return new Developer.Builder(name,Role.TESTER)
                        .university(getUniversity())
                        .sex(getSex())
                        .country(getCountry())
                        .telephoneNumber(getTelephoneNumber())
                        .email(getEmail(name))
                        .build();
            }
            case "teamManager": {
                String name = generateString();
                return new TeamManager.Builder(name,Role.TEAM_LEADER,Generator.getNumberOfMaxEmployees())
                        .university(getUniversity())
                        .sex(getSex())
                        .country(getCountry())
                        .telephoneNumber(getTelephoneNumber())
                        .email(getEmail(name))
                        .build();
            }
            case "ceo": {
                String name = generateString();
                return new TeamManager.Builder(name,Role.CEO,Generator.getNumberOfMaxEmployees())
                        .university(getUniversity())
                        .sex(getSex())
                        .country(getCountry())
                        .telephoneNumber(getTelephoneNumber())
                        .email(getEmail(name))
                        .build();
            }
            default: {
                String name = generateString();
                return new Developer.Builder(name,Role.CONTRIBUTOR)
                        .university(getUniversity())
                        .sex(getSex())
                        .country(getCountry())
                        .telephoneNumber(getTelephoneNumber())
                        .email(getEmail(name))
                        .build();
            }
        }
    }

    private static String generateString() {
        Random random = new Random();
        return names.get(random.nextInt(names.size()));
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

    private static String getEmail(String name){
        Random random = new Random();
        return name + emailDomain.get(random.nextInt(emailDomain.size()));
    }
}
