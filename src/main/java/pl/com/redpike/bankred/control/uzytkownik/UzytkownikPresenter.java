package pl.com.redpike.bankred.control.uzytkownik;

import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.business.uzytkownik.UzytkownikDAO;
import pl.com.redpike.bankred.presentation.components.presenters.AbstractPresenter;
import pl.com.redpike.bankred.presentation.uzytkownik.UzytkownikView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Redpike
 */
public class UzytkownikPresenter extends AbstractPresenter<UzytkownikView> {

    @Inject
    private UzytkownikDAO uzytkownikDAO;

    public List<Uzytkownik> getAllUzytkownicy() {
        return uzytkownikDAO.findAll();
    }

    public Uzytkownik getUzytkownik(String username) {
        return uzytkownikDAO.getUzytkownik(username);
    }

    public Uzytkownik getUzytkownikOnLogIn(String username, String password) {
        return uzytkownikDAO.getUzytkownikOnLogIn(username, password);
    }

    public void addUzytkownik(Uzytkownik uzytkownik) {
        uzytkownikDAO.addUzytkownik(uzytkownik);
    }

    public void editUzytkownik(Uzytkownik uzytkownik) {
        uzytkownikDAO.editUzytkownik(uzytkownik);
    }

    public void removeUzytkownik(Uzytkownik uzytkownik) {
        uzytkownikDAO.removeUzytkownik(uzytkownik);
    }
}