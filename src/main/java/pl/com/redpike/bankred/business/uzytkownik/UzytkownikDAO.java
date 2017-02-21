package pl.com.redpike.bankred.business.uzytkownik;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by rs3 on 21.02.2017.
 */
@Local
public interface UzytkownikDAO {

    List<Uzytkownik> findAll();
}
