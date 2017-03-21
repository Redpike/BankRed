package pl.com.redpike.bankred.presentation.home;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;

import javax.annotation.PostConstruct;

/**
 * Created by rs3 on 22.02.2017.
 */
@CDIView(HomePage.VIEW_ID)
@ViewMenuItem(title = "Pulpit", order = 0, icon = FontAwesome.BANK)
public class HomePage extends CustomComponent implements View {

    public static final String VIEW_ID = "home";

    private Navigator navigator;

    @PostConstruct
    private void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new HomeView(this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = getUI().getNavigator();
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
