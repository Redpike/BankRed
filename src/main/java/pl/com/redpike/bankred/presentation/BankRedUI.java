package pl.com.redpike.bankred.presentation;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.cdiviewmenu.ViewMenuUI;
import org.vaadin.viritin.button.MButton;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.control.event.login.LoggedUserEvent;
import pl.com.redpike.bankred.presentation.error.ErrorPage;
import pl.com.redpike.bankred.presentation.home.HomePage;
import pl.com.redpike.bankred.presentation.login.LoginPage;
import pl.com.redpike.bankred.util.styles.Styles;

import javax.enterprise.event.Observes;

/**
 * Created by rs3 on 21.02.2017.
 */
@CDIUI("")
@Title("BankRed")
@Theme("bankred")
@Widgetset("pl.com.redpike.bankred.BankRedWidgetset")
@PreserveOnRefresh
public class BankRedUI extends ViewMenuUI {

    private MenuBar.MenuItem userMenu;
    private Button logoutButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        super.init(vaadinRequest);

        ViewMenuUI.getMenu().setVisible(false);
        ViewMenuUI.getMenu().navigateTo(LoginPage.VIEW_ID);
        ViewMenuUI.getCurrent().setErrorHandler((ErrorHandler) errorEvent -> ViewMenuUI.getMenu().navigateTo(ErrorPage.VIEW_ID));
    }

    public void userLoggedIn(@Observes LoggedUserEvent event) {
        initLogoutButton();
        VaadinSession.getCurrent().setAttribute("token", event.getUzytkownik());
        ViewMenuUI.getMenu().setVisible(true);
        ViewMenuUI.getMenu().setMenuTitle("BankRed System");
        ViewMenuUI.getMenu().setSecondaryComponent(prepareLoggedUserComponent(event.getUzytkownik()));
        ViewMenuUI.getMenu().navigateTo(HomePage.VIEW_ID);
        ViewMenuUI.getMenu().addMenuItem(logoutButton);
        Notification.show("Witaj " + event.getNameAndSurname(), Notification.Type.TRAY_NOTIFICATION);
    }

    private void initLogoutButton() {
        logoutButton = new MButton().withCaption("Wyloguj").withIcon(FontAwesome.SIGN_OUT).withStyleName(ValoTheme.BUTTON_SMALL);
        logoutButton.addClickListener(event -> logout());
    }

    private Component prepareLoggedUserComponent(Uzytkownik uzytkownik) {
        MenuBar userMenuBar = new MenuBar();
        userMenuBar.addStyleName(Styles.USER_MENU);
        userMenu = userMenuBar.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);
        userMenu.setText(uzytkownik.getImie() + " " + uzytkownik.getNazwisko());

        return userMenuBar;
    }

    public void logout() {
        VaadinSession.getCurrent().close();
        Page.getCurrent().setLocation(LoginPage.VIEW_ID);
        ViewMenuUI.getMenu().setVisible(false);
        Notification.show("Wylogowano", Notification.Type.TRAY_NOTIFICATION);
    }
}
