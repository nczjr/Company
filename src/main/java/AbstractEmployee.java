import javafx.util.Builder;

public abstract class AbstractEmployee implements Employee {
    private final String name;
    private final RoleType role;
    private final Report report;
    private final String university;
    private final GenderType gender;
    private final String country;
    private final String telephoneNumber;

    public AbstractEmployee(Builder builder){
        this.name = builder.name;
        this.role = builder.role;
        this.report = new Report();
        this.university = builder.university;
        this.gender = builder.gender;
        this.country = builder.country;
        this.telephoneNumber = builder.telephoneNumber;
    }

//    AbstractEmployee(String name, RoleType role){
//        this.name = name;
//        this.role = role;
//        report = new Report();
//    }


    @Override
    public RoleType getRole() {
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
    public GenderType getGender() {
        return gender;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Report reportWork(){
        return this.report;
    }

    @Override
    public String toString() {
        return "AbstractEmployee{" +
                "name='" + name + '\'' +
                ", role=" + role +
                ", report=" + report +
                ", university='" + university + '\'' +
                ", gender=" + gender +
                ", country='" + country + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    public static abstract class Builder {
        private String name;
        private RoleType role;
        private String university;
        private GenderType gender;
        private String country;
        private String telephoneNumber;
        private String email;

        public Builder(){

        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder role(RoleType role) {
            this.role = role;
            return this;
        }

        public Builder university(String university) {
            this.university = university;
            return this;
        }

        public Builder gender(GenderType gender) {
            this.gender = gender;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder telephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public Builder email(String email){
            this.telephoneNumber = email;
            return this;
        }

        //public abstract Builder getThis();

        public abstract AbstractEmployee build();


    }

}
