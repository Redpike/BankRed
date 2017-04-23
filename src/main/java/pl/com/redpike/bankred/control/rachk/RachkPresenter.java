package pl.com.redpike.bankred.control.rachk;

import pl.com.redpike.bankred.business.klient.Klient;
import pl.com.redpike.bankred.business.klient.KlientDAO;
import pl.com.redpike.bankred.business.rachk.Rachk;
import pl.com.redpike.bankred.business.rachk.RachkDAO;
import pl.com.redpike.bankred.presentation.components.presenters.AbstractPresenter;
import pl.com.redpike.bankred.presentation.rachk.RachkView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Redpike
 */
public class RachkPresenter extends AbstractPresenter<RachkView> {

    @Inject
    private RachkDAO rachkDAO;

    @Inject
    private KlientDAO klientDAO;

    public List<Rachk> findAll() {
        return rachkDAO.findAll();
    }

    public void addRachk(Rachk rachk) {
        rachkDAO.addRachunek(rachk);
    }

    public void editRachk(Rachk rachk) {
        rachkDAO.editRachunek(rachk);
    }

    public void removeRachk(Rachk rachk) {
        rachkDAO.removeRachunek(rachk);
    }

    public List<Klient> findAllKlient() {
        return klientDAO.findAll();
    }
}
