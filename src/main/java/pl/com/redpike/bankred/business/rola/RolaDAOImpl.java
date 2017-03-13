package pl.com.redpike.bankred.business.rola;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Redpike
 */
@Stateless
public class RolaDAOImpl implements RolaDAO {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    @Override
    public List<Rola> findAll() {
        return em.createNativeQuery("SELECT * FROM Rola r", Rola.class)
                .getResultList();
    }
}
