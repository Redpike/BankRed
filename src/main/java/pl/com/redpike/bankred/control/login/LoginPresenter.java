package pl.com.redpike.bankred.control.login;

import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.control.uzytkownik.UzytkownikPresenter;

import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created by Redpike
 */
public class LoginPresenter {

    @Inject
    private UzytkownikPresenter uzytkownikPresenter;

    @Inject
    private Event<LoggedUserEvent> loggedUserEvent;

    public Uzytkownik getUzytkownik(String username) {
        return uzytkownikPresenter.getUzytkownik(username);
    }

    public Uzytkownik getUzytkownikOnLogIn(String username, String password) {
        return uzytkownikPresenter.getUzytkownikOnLogIn(username, password);
    }

    public void onLoginButtonPressed(String name, String surname) {
        loggedUserEvent.fire(new LoggedUserEvent(name, surname));
    }
}
