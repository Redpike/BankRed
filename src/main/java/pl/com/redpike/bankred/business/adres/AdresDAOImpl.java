package pl.com.redpike.bankred.business.adres;

import pl.com.redpike.bankred.util.properties.BankRedProperites;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Redpike
 */
@Stateless
public class AdresDAOImpl implements AdresDAO {

    @PersistenceContext(unitName = BankRedProperites.BANKRED_PU)
    private EntityManager em;

    @Override
    public void addAdres(Adres adres) {

    }

    @Override
    public void editAdres(Adres adres) {

    }
}
