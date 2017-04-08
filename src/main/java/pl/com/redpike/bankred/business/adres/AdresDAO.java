package pl.com.redpike.bankred.business.adres;

import javax.ejb.Local;

/**
 * Created by Redpike
 */
@Local
public interface AdresDAO {

    void addAdres(Adres adres);

    void editAdres(Adres adres);
}
