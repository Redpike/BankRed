package pl.com.redpike.bankred.business.klient;

import pl.com.redpike.bankred.util.properties.BankRedProperites;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Redpike
 */
@Stateless
public class KlientDAOImpl implements KlientDAO{

    @PersistenceContext(unitName = BankRedProperites.BANKRED_PU)
    private EntityManager em;

    @Override
    public List<Klient> findAll() {
        return em.createNativeQuery("SELECT * FROM Klient k", Klient.class)
                .getResultList();
    }

    @Override
    public Klient getKlient(BigDecimal modulo) {
        return null;
    }

    @Override
    public void addKlient(Klient klient) {

    }

    @Override
    public void editKlient(Klient klient) {

    }

    @Override
    public void removeKlient(Klient klient) {

    }
}
