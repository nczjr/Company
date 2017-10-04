package employee;


public abstract class Builder<T extends Builder<T>>{
    private String name;
    private Role role;
    private String university;
    private Sex sex;
    private String country;
    private String telephoneNumber;
    private String email;


    public Builder(){

    }

    public Builder(String name, Role role){
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
