package employee;

import task.Report;

public abstract class AbstractEmployee implements Employee {
    private final String name;
    private final Role role;
    private final Report report;
    private final String university;
    private final Sex sex;
    private final String country;
    private final String telephoneNumber;
    private final String email;

    AbstractEmployee(Builder builder){
        this.name = builder.name;
        this.role = builder.role;
        this.report = new Report();
        this.university = builder.university;
        this.sex = builder.sex;
        this.country = builder.country;
        this.telephoneNumber = builder.telephoneNumber;
        this.email = builder.email;
    }

    AbstractEmployee(String name, Role role){
        this.name = name;
        this.role = role;
        this.report = new Report();
        this.university = "";
        this.sex = Sex.FEMALE;
        this.country = "";
        this.email = name + "@gmail.com";
        this.telephoneNumber = "123456789";
    }


    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Report getReport() {
        return report;
    }

    @Override
    public String getUniversity() {
        return university;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public String getCountry() { return country; }

    @Override
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() { return email; }

    public Report reportWork(){
        return this.report;
    }


    @Override
    public String toString() {
        return "Name = " + name  +
                ", role = " + role +
                ", university = " + university +
                ", sex = " + sex +
                ", country = " + country +
                ", telephoneNumber = " + telephoneNumber +
                ", email = " + email +
                ", report = " + report ;
    }

    public abstract static class Builder<T extends Builder<T>>{
        private String name;
        private Role role;
        private String university;
        private Sex sex;
        private String country;
        private String telephoneNumber;
        private String email;


        Builder(){

        }

        Builder(String name, Role role){
            this.name = name;
            this.role = role;
        }

        public T name(String name) {
            this.name = name;
            return getThis();
        }

        public T role(Role role) {
            this.role = role;
            return getThis();
        }

        public T university(String university) {
            this.university = university;
            return getThis();
        }

        public T sex(Sex sex) {
            this.sex = sex;
            return getThis();
        }

        public T country(String country) {
            this.country = country;
            return getThis();
        }

        public T telephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return getThis();
        }

        public T email(String email){
            this.email = email;
            return getThis();
        }
        public abstract T getThis();

        public abstract AbstractEmployee build();


    }

}
