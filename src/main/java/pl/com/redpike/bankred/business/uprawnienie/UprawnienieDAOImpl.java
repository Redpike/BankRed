package pl.com.redpike.bankred.business.uprawnienie;

import pl.com.redpike.bankred.util.properties.BankRedProperites;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Redpike
 */
@Stateless
public class UprawnienieDAOImpl implements UprawnienieDAO {

    @PersistenceContext(unitName = BankRedProperites.BANKRED_PU)
    private EntityManager em;

    @Override
    public List<Uprawnienie> getAllUprawnienia() {
        return em.createNativeQuery("SELECT * FROM Uprawnienie u", Uprawnienie.class)
                .getResultList();
    }

    @Override
    public void addUprawnienie(Uprawnienie uprawnienie) {
        em.persist(uprawnienie);
        em.flush();
    }

    @Override
    public void editUprawnienie(Uprawnienie uprawnienie) {
        em.merge(uprawnienie);
        em.flush();
    }

    @Override
    public void removeUprawnienie(Uprawnienie uprawnienie) {
        uprawnienie = em.getReference(Uprawnienie.class, uprawnienie.getId());
        em.remove(uprawnienie);
        em.flush();
    }
}
