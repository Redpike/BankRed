package pl.com.redpike.bankred.presentation;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.cdiviewmenu.ViewMenuUI;
import org.vaadin.viritin.button.MButton;
import pl.com.redpike.bankred.control.event.login.LoggedUserEvent;
import pl.com.redpike.bankred.presentation.home.HomePage;
import pl.com.redpike.bankred.presentation.login.LoginPage;

import javax.enterprise.event.Observes;

/**
 * Created by rs3 on 21.02.2017.
 */
@CDIUI("")
@Title("BankRed")
@Theme("bankred")
@PreserveOnRefresh
public class BankRedUI extends ViewMenuUI {

    private Button logoutButton;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        super.init(vaadinRequest);

        ViewMenuUI.getMenu().setVisible(false);
        ViewMenuUI.getMenu().navigateTo(LoginPage.VIEW_ID);
    }

    public void userLoggedIn(@Observes LoggedUserEvent event) {
        initLogoutButton();
        ViewMenuUI.getMenu().setVisible(true);
        ViewMenuUI.getMenu().setMenuTitle("BankRed System");
        ViewMenuUI.getMenu().navigateTo(HomePage.VIEW_ID);
        ViewMenuUI.getMenu().addMenuItem(logoutButton);
        Notification.show("Witaj " + event.getNameAndSurname(), Notification.Type.TRAY_NOTIFICATION);
    }

    private void initLogoutButton() {
        logoutButton = new MButton().withCaption("Wyloguj").withIcon(FontAwesome.SIGN_OUT).withStyleName(ValoTheme.BUTTON_SMALL);
        logoutButton.addClickListener(event -> logout());
    }

    private void logout() {
        VaadinSession.getCurrent().close();
        Page.getCurrent().setLocation(LoginPage.VIEW_ID);
        ViewMenuUI.getMenu().setVisible(false);
        Notification.show("Wylogowano", Notification.Type.TRAY_NOTIFICATION);
    }
}
