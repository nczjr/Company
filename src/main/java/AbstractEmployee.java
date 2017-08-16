public abstract class AbstractEmployee implements Employee {
    private final String name;
    private final RoleType role;
    private final Report report;
    private final String university;
    private final Sex sex;
    private final String country;
    private final String telephoneNumber;
    private final String email;

    public AbstractEmployee(Builder builder){
        this.name = builder.name;
        this.role = builder.role;
        this.report = new Report();
        this.university = builder.university;
        this.sex = builder.gender;
        this.country = builder.country;
        this.telephoneNumber = builder.telephoneNumber;
        this.email = builder.email;
    }


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

    public static abstract class Builder {
        private String name;
        private RoleType role;
        private String university;
        private Sex gender;
        private String country;
        private String telephoneNumber;
        private String email;


        public Builder(){

        }

        public Builder(String name, RoleType role){
            this.name = name;
            this.role = role;
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

        public Builder gender(Sex gender) {
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
            this.email = email;
            return this;
        }


        public abstract AbstractEmployee build();


    }

}
