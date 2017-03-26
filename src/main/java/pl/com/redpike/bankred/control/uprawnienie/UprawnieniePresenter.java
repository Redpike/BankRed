package pl.com.redpike.bankred.control.uprawnienie;

import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.business.rola.RolaDAO;
import pl.com.redpike.bankred.business.uprawnienie.Uprawnienie;
import pl.com.redpike.bankred.business.uprawnienie.UprawnienieDAO;
import pl.com.redpike.bankred.presentation.components.presenters.AbstractPresenter;
import pl.com.redpike.bankred.presentation.uprawnienie.UprawnienieTabView;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

/**
 * Created by Redpike
 */
public class UprawnieniePresenter extends AbstractPresenter<UprawnienieTabView> {

    @Inject
    private UprawnienieDAO uprawnienieDAO;

    @Inject
    private RolaDAO rolaDAO;

    public List<Uprawnienie> getAllUprawnienia() {
        return uprawnienieDAO.getAllUprawnienia();
    }

    public void addUprawnienie(Uprawnienie uprawnienie) {
        uprawnienieDAO.addUprawnienie(uprawnienie);
    }

    public void editUprawnienie(Uprawnienie uprawnienie) {
        uprawnienieDAO.editUprawnienie(uprawnienie);
    }

    public void removeUprawnienie(Uprawnienie uprawnienie) {
        uprawnienieDAO.removeUprawnienie(uprawnienie);
    }

    public Set<Uprawnienie> getAllUprawnieniaForRola(Rola rola) {
        return uprawnienieDAO.getAllUprawnieniaForRola(rola);
    }

    public List<Rola> findAllRola() {
        return rolaDAO.findAll();
    }

    public void addUprawnienieForRola(Uprawnienie uprawnienie, Rola rola) {
        uprawnienieDAO.addUprawnienieForRola(uprawnienie, rola);
    }

    public void removeUprawnienieForRola(Uprawnienie uprawnienie, Rola rola) {
        uprawnienieDAO.removeUprawnienieForRola(uprawnienie, rola);
    }
}
