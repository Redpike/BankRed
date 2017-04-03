package pl.com.redpike.bankred.control.home;

import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.control.login.LoginPresenter;
import pl.com.redpike.bankred.control.uzytkownik.UzytkownikWindowPresenter;
import pl.com.redpike.bankred.presentation.components.presenters.AbstractPresenter;
import pl.com.redpike.bankred.presentation.home.HomeView;

import javax.inject.Inject;

/**
 * Created by Redpike
 */
public class HomePresenter extends AbstractPresenter<HomeView> {

    @Inject
    private LoginPresenter loginPresenter;

    @Inject
    private UzytkownikWindowPresenter uzytkownikWindowPresenter;

    public void refreshUzytkownikPanel(Uzytkownik uzytkownik) {
        getView().refreshUzytkownikData(uzytkownik);
    }

    public LoginPresenter getLoginPresenter() {
        return loginPresenter;
    }

    public UzytkownikWindowPresenter getUzytkownikWindowPresenter() {
        return uzytkownikWindowPresenter;
    }
}
