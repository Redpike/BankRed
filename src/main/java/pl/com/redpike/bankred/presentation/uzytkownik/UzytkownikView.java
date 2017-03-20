package pl.com.redpike.bankred.presentation.uzytkownik;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import pl.com.redpike.bankred.control.uzytkownik.UzytkownikWindowPresenter;
import pl.com.redpike.bankred.control.uzytkownik.UzytkownikPresenter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by rs3 on 22.02.2017.
 */
@CDIView(UzytkownikView.VIEW_ID)
@ViewMenuItem(title = "Uzytkownicy", order = 1, icon = FontAwesome.USERS)
public class UzytkownikView extends CustomComponent implements View {

    public static final String VIEW_ID = "uzytkownik";

    @Inject
    private UzytkownikPresenter uzytkownikPresenter;

    @Inject
    private UzytkownikWindowPresenter uzytkownikWindowPresenter;

    @PostConstruct
    private void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new UzytkownikPage(uzytkownikPresenter, this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public UzytkownikPresenter getUzytkownikPresenter() {
        return uzytkownikPresenter;
    }

    public UzytkownikWindowPresenter getUzytkownikWindowPresenter() {
        return uzytkownikWindowPresenter;
    }
}
