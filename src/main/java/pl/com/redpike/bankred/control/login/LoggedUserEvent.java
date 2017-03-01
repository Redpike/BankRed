package pl.com.redpike.bankred.control.login;

/**
 * Created by Redpike
 */
public class LoggedUserEvent {

    private String name;
    private String surname;

    public LoggedUserEvent(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNameAndSurname() {
        return name + " " + surname;
    }
}
