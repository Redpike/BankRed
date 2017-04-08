package pl.com.redpike.bankred.presentation.klient;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import pl.com.redpike.bankred.control.klient.KlientPresenter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by Redpike
 */
@CDIView(KlientPage.VIEW_ID)
@ViewMenuItem(title = "Klienci", order = 5, icon = FontAwesome.USERS)
public class KlientPage extends CustomComponent implements View {

    public static final String VIEW_ID = "klient";

    @Inject
    private KlientPresenter klientPresenter;

    @PostConstruct
    public void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new KlientView(klientPresenter, this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public KlientPresenter getKlientPresenter() {
        return klientPresenter;
    }
}
