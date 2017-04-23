package pl.com.redpike.bankred.business.klient;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Redpike
 */
@Local
public interface KlientDAO {

    List<Klient> findAll();

    void addKlient(Klient klient);

    void editKlient(Klient klient);

    void removeKlient(Klient klient);
}
