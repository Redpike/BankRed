package pl.com.redpike.bankred.business.rola;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Redpike
 */
@Local
public interface RolaDAO {

    List<Rola> findAll();

    void addRola(Rola rola);

    void editRola(Rola rola);

    void removeRola(Rola rola);
}
