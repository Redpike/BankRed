package pl.com.redpike.bankred.control.uzytkownik;

import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.business.uzytkownik.UzytkownikDAO;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Redpike
 */
public class UzytkownikPresenter {

    @Inject
    private UzytkownikDAO uzytkownikDAO;

    public List<Uzytkownik> getAllUzytkowniki() {
        return uzytkownikDAO.findAll();
    }

    public Uzytkownik getUzytkownik(String username) {
        return uzytkownikDAO.getUzytkownik(username);
    }

    public Uzytkownik getUzytkownikOnLogIn(String username, String password) {
        return uzytkownikDAO.getUzytkownikOnLogIn(username, password);
    }

    public void removeUzytkownik(Uzytkownik uzytkownik) {
        uzytkownikDAO.removeUzytkownik(uzytkownik);
    }
}
