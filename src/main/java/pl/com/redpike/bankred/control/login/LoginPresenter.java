package pl.com.redpike.bankred.control.login;

import com.google.common.eventbus.EventBus;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.control.event.login.LoggedUserEvent;
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

    private EventBus eventBus;

    public Uzytkownik getUzytkownik(String username) {
        return uzytkownikPresenter.getUzytkownik(username);
    }

    public Uzytkownik getUzytkownikOnLogIn(String username, String password) {
        return uzytkownikPresenter.getUzytkownikOnLogIn(username, password);
    }

    public void onLoginButtonPressed(Uzytkownik uzytkownik) {
        loggedUserEvent.fire(new LoggedUserEvent(uzytkownik.getImie(), uzytkownik.getNazwisko()));
    }
}
