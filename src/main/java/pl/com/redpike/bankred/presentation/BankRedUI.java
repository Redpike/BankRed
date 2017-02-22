package pl.com.redpike.bankred.presentation;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.teemusa.sidemenu.SideMenu;
import org.vaadin.teemusa.sidemenu.SideMenuUI;
import pl.com.redpike.bankred.presentation.home.HomeView;
import pl.com.redpike.bankred.presentation.login.LoginView;

import javax.inject.Inject;

/**
 * Created by rs3 on 21.02.2017.
 */
@CDIUI("")
@SideMenuUI
@Title("BankRed System")
@Theme("bankred")
public class BankRedUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;

    private Navigator navigator;
    private SideMenu sideMenu;
    private Label appNameLabel;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        appNameLabel = new Label();
        appNameLabel.setCaptionAsHtml(true);
        appNameLabel.setCaption("<i><b>BankRed</b> System</i>");

        sideMenu = new SideMenu();
        setContent(sideMenu);

        navigator = new Navigator(this, sideMenu);
        navigator.addProvider(cdiViewProvider);
        setNavigator(navigator);
        navigator.addView(LoginView.VIEW_ID, LoginView.class);
        navigator.addView(HomeView.VIEW_ID, HomeView.class);

        sideMenu.setMenuCaption(appNameLabel.getCaption());
        sideMenu.addNavigation("Login", FontAwesome.USER, LoginView.VIEW_ID);
        sideMenu.addNavigation("Home", FontAwesome.HOME, HomeView.VIEW_ID);
    }
}
