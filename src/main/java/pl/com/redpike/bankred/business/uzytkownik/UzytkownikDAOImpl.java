package pl.com.redpike.bankred.business.uzytkownik;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by rs3 on 21.02.2017.
 */
@Stateless
public class UzytkownikDAOImpl implements UzytkownikDAO {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    @Override
    public List<Uzytkownik> findAll() {
        return em.createNativeQuery("SELECT* FROM Uzytkownik u", Uzytkownik.class).getResultList();
    }
}
