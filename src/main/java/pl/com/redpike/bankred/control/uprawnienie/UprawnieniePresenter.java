package pl.com.redpike.bankred.control.uprawnienie;

import pl.com.redpike.bankred.business.uprawnienie.Uprawnienie;
import pl.com.redpike.bankred.business.uprawnienie.UprawnienieDAO;
import pl.com.redpike.bankred.presentation.components.presenters.AbstractPresenter;
import pl.com.redpike.bankred.presentation.uprawnienie.UprawnienieTabPage;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Redpike
 */
public class UprawnieniePresenter extends AbstractPresenter<UprawnienieTabPage> {

    @Inject
    private UprawnienieDAO uprawnienieDAO;

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
}
