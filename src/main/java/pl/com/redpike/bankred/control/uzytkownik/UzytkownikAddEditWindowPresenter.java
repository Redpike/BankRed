package pl.com.redpike.bankred.control.uzytkownik;

import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.business.rola.RolaDAO;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Redpike
 */
public class UzytkownikAddEditWindowPresenter {

    @Inject
    private RolaDAO rolaDAO;

    @Inject
    private UzytkownikPresenter uzytkownikPresenter;

    public List<Rola> getRoles() {
        return rolaDAO.findAll();
    }
}
