public interface Employee {
    Role getRole();
    String getName();
    void assign(Task... task);
    Report reportWork();
    Report getReport();
    String getUniversity();
    Sex getSex();
    String getCountry();
    String getTelephoneNumber();
    String getEmail();

}
