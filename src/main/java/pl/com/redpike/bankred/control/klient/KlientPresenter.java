package pl.com.redpike.bankred.control.klient;

import pl.com.redpike.bankred.business.klient.Klient;
import pl.com.redpike.bankred.business.klient.KlientDAO;
import pl.com.redpike.bankred.presentation.components.presenters.AbstractPresenter;
import pl.com.redpike.bankred.presentation.klient.KlientView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Redpike
 */
public class KlientPresenter extends AbstractPresenter<KlientView> {

    @Inject
    private KlientDAO klientDAO;

    public List<Klient> findAll() {
        return klientDAO.findAll();
    }

    public void addKlient(Klient klient) {
        klientDAO.addKlient(klient);
    }

    public void editKlient(Klient klient) {
        klientDAO.editKlient(klient);
    }

    public void removeKlient(Klient klient) {
        klientDAO.removeKlient(klient);
    }
}
