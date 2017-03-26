package pl.com.redpike.bankred.business.uprawnienie;

import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.util.properties.BankRedProperites;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public Set<Uprawnienie> getAllUprawnieniaForRola(Rola rola) {
        return new HashSet<>(em.createNativeQuery("SELECT * FROM Uprawnienie u\n" +
                "JOIN rola_uprawnienie ON rola_uprawnienie.uprawnienie_id = u.uprawnienie_id\n" +
                "WHERE rola_uprawnienie.rola_id = :rolaId", Uprawnienie.class)
                .setParameter("rolaId", rola.getId())
                .getResultList());
    }

    @Override
    public void addUprawnienieForRola(Uprawnienie uprawnienie, Rola rola) {
        em.createNativeQuery("INSERT INTO rola_uprawnienie ru VALUES(:rolaId, :uprawnienieId)")
                .setParameter("uprawnienieId", uprawnienie.getId())
                .setParameter("rolaId", rola.getId())
                .executeUpdate();
        em.flush();
    }

    @Override
    public void removeUprawnienieForRola(Uprawnienie uprawnienie, Rola rola) {
        em.createNativeQuery("DELETE FROM rola_uprawnienie ru WHERE ru.uprawnienie_id = :uprawnienieId AND ru.rola_id = :rolaId")
                .setParameter("uprawnienieId", uprawnienie.getId())
                .setParameter("rolaId", rola.getId())
                .executeUpdate();
        em.flush();
    }
}
