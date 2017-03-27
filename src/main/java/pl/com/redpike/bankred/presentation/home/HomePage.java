package pl.com.redpike.bankred.presentation.home;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.control.event.login.LoggedUserEvent;
import pl.com.redpike.bankred.control.home.HomePresenter;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Created by rs3 on 22.02.2017.
 */
@CDIView(HomePage.VIEW_ID)
@ViewMenuItem(title = "Pulpit", order = 0, icon = FontAwesome.BANK)
public class HomePage extends CustomComponent implements View {

    public static final String VIEW_ID = "home";

    @Inject
    private HomePresenter homePresenter;

    private Navigator navigator;
    private Uzytkownik uzytkownik;

    @PostConstruct
    private void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new HomeView(homePresenter, this));
    }

    public void getUzytkownikPanelData(@Observes LoggedUserEvent event) {
        uzytkownik = event.getUzytkownik();
        homePresenter.refreshUzytkownikPanel(uzytkownik);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = getUI().getNavigator();
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public HomePresenter getHomePresenter() {
        return homePresenter;
    }
}
