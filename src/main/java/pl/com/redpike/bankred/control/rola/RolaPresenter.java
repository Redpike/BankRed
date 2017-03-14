package pl.com.redpike.bankred.control.rola;

import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.business.rola.RolaDAO;
import pl.com.redpike.bankred.presentation.components.presenters.AbstractPresenter;
import pl.com.redpike.bankred.presentation.rola.RolaPage;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Redpike
 */
public class RolaPresenter extends AbstractPresenter<RolaPage> {

    @Inject
    private RolaDAO rolaDAO;

    public List<Rola> getAllRole() {
        return rolaDAO.findAll();
    }

    public void addRola(Rola rola) {
        rolaDAO.addRola(rola);
    }

    public void editRola(Rola rola) {
        rolaDAO.editRola(rola);
    }

    public void removeRola(Rola rola) {
        rolaDAO.removeRola(rola);
    }
}
