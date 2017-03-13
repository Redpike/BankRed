package pl.com.redpike.bankred.business.uzytkownik;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by rs3 on 21.02.2017.
 */
@Local
public interface UzytkownikDAO {

    List<Uzytkownik> findAll();

    Uzytkownik getUzytkownik(String username);

    Uzytkownik getUzytkownikOnLogIn(String username, String password);

    void addUzytkownik(Uzytkownik uzytkownik);

    void editUzytkownik(Uzytkownik uzytkownik);

    void removeUzytkownik(Uzytkownik uzytkownik);
}
