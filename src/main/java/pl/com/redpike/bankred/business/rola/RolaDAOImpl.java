package pl.com.redpike.bankred.business.rola;

import pl.com.redpike.bankred.util.properties.BankRedProperites;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Redpike
 */
@Stateless
public class RolaDAOImpl implements RolaDAO {

    @PersistenceContext(unitName = BankRedProperites.BANKRED_PU)
    private EntityManager em;

    @Override
    public List<Rola> findAll() {
        return em.createNativeQuery("SELECT * FROM Rola r", Rola.class)
                .getResultList();
    }

    @Override
    public void addRola(Rola rola) {
        em.persist(rola);
        em.flush();
    }

    @Override
    public void editRola(Rola rola) {
        em.merge(rola);
        em.flush();
    }

    @Override
    public void removeRola(Rola rola) {
        rola = em.getReference(Rola.class, rola.getId());
        em.remove(rola);
        em.flush();
    }
}
