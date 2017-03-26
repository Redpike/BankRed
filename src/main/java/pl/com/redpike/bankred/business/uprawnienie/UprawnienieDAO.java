package pl.com.redpike.bankred.business.uprawnienie;

import pl.com.redpike.bankred.business.rola.Rola;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

/**
 * Created by Redpike
 */
@Local
public interface UprawnienieDAO {

    List<Uprawnienie> getAllUprawnienia();

    void addUprawnienie(Uprawnienie uprawnienie);

    void editUprawnienie(Uprawnienie uprawnienie);

    void removeUprawnienie(Uprawnienie uprawnienie);

    Set<Uprawnienie> getAllUprawnieniaForRola(Rola rola);

    void addUprawnienieForRola(Uprawnienie uprawnienie, Rola rola);

    void removeUprawnienieForRola(Uprawnienie uprawnienie, Rola rola);
}
