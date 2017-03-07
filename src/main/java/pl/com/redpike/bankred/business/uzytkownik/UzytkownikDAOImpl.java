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
        return em.createNativeQuery("SELECT * FROM Uzytkownik u", Uzytkownik.class).getResultList();
    }

    @Override
    public Uzytkownik getUzytkownik(String username) {
        return em.createQuery("FROM Uzytkownik u WHERE u.nazwa = :username", Uzytkownik.class)
                .setParameter("username", username)
                .getResultList().get(0);
    }

    @Override
    public Uzytkownik getUzytkownikOnLogIn(String username, String password) {
        return em.createQuery("FROM Uzytkownik u where u.nazwa = :username AND u.haslo = :password", Uzytkownik.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList().get(0);
    }

    @Override
    public void removeUzytkownik(Uzytkownik uzytkownik) {
        uzytkownik = em.getReference(Uzytkownik.class, uzytkownik.getId());
        em.remove(uzytkownik);
    }
}
