package pl.com.redpike.bankred.presentation;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.vaadin.teemusa.sidemenu.SideMenu;
import pl.com.redpike.bankred.control.login.LoggedUserEvent;
import pl.com.redpike.bankred.presentation.home.HomeView;
import pl.com.redpike.bankred.presentation.login.LoginView;
import pl.com.redpike.bankred.presentation.uzytkownik.UzytkownikView;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Created by rs3 on 21.02.2017.
 */
@CDIUI("")
@Title("BankRed")
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
        sideMenu.setMenuCaption(appNameLabel.getCaption());

        navigator = new Navigator(this, sideMenu);
        navigator.addProvider(cdiViewProvider);
        setNavigator(navigator);
        navigator.addView(LoginView.VIEW_ID, LoginView.class);
        navigator.addView(HomeView.VIEW_ID, HomeView.class);

        setContent(sideMenu);
    }

    public void userLoggedIn(@Observes LoggedUserEvent event) {
        Notification.show("Witaj " + event.getNameAndSurname(), Notification.Type.TRAY_NOTIFICATION);
        setUser(event);
        sideMenu.removeAllComponents();
        sideMenu.addNavigation("Użytkownicy", FontAwesome.USERS, UzytkownikView.VIEW_ID);
        navigator.addView(UzytkownikView.VIEW_ID, UzytkownikView.class);
        navigator.navigateTo(HomeView.VIEW_ID);
    }

    private void setUser(LoggedUserEvent event) {
        sideMenu.setUserIcon(FontAwesome.MALE);
        sideMenu.setUserName(event.getNameAndSurname());
        sideMenu.clearUserMenu();
        sideMenu.addUserMenuItem("Ustawienia konta", FontAwesome.WRENCH, () -> Notification.show("Ustawienia konta", Notification.Type.TRAY_NOTIFICATION));
        sideMenu.addUserMenuItem("Wyloguj", FontAwesome.SIGN_OUT, () -> Notification.show("Wylogowano", Notification.Type.TRAY_NOTIFICATION));
    }
}
