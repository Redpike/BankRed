package pl.com.redpike.bankred.presentation.uzytkownik;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;

import javax.inject.Inject;

/**
 * Created by Redpike
 */
public class UzytkownikAddEditWindow extends Window {

    @Inject
    private UzytkownikForm uzytkownikForm;

    public UzytkownikAddEditWindow() {
        setCaption( "Dodaj/Edytuj użytkownika");
        setIcon(FontAwesome.USER);
        setModal(true);
        setResizable(false);
        setHeight(550, Unit.PIXELS);
        setWidth(400, Unit.PIXELS);
        setContent(uzytkownikForm);
    }

    public void openForSelectedUzytkownik(Uzytkownik uzytkownik) {
        UI.getCurrent().addWindow(this);
    }
}
