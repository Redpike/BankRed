package pl.com.redpike.bankred.control.event.login;

import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;

/**
 * Created by Redpike
 */
public class LoggedUserEvent {

    private Uzytkownik uzytkownik;

    public LoggedUserEvent(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public String getNameAndSurname() {
        return uzytkownik.getImie() + " " + uzytkownik.getNazwisko();
    }
}
