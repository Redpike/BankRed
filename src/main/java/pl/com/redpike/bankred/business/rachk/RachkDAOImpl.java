package pl.com.redpike.bankred.business.rachk;

import pl.com.redpike.bankred.util.properties.BankRedProperites;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Redpike
 */
@Stateless
public class RachkDAOImpl implements RachkDAO {

    @PersistenceContext(unitName = BankRedProperites.BANKRED_PU)
    private EntityManager em;

    @Override
    public List<Rachk> findAll() {
        return em.createNativeQuery("SELECT * FROM Rachk r", Rachk.class).getResultList();
    }

    @Override
    public void addRachunek(Rachk rachk) {
        em.persist(rachk);
        em.flush();
    }

    @Override
    public void editRachunek(Rachk rachk) {
        em.merge(rachk);
        em.flush();
    }

    @Override
    public void removeRachunek(Rachk rachk) {
        rachk = em.getReference(Rachk.class, rachk.getRachunek());
        em.remove(rachk);
    }
}
