package pl.com.redpike.bankred.control.adres;

import pl.com.redpike.bankred.business.adres.Adres;
import pl.com.redpike.bankred.business.adres.AdresDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by Redpike
 */
@RequestScoped
public class AdresWindowPresenter {

    @Inject
    private AdresDAO adresDAO;

    public void addAdres(Adres adres) {
        adresDAO.addAdres(adres);
    }

    public void editAdres(Adres adres) {
        adresDAO.editAdres(adres);
    }
}
