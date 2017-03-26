package pl.com.redpike.bankred.presentation.login;

import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.enums.UzytkownikZablokowanyEnum;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.security.PasswordProvider;

import javax.ejb.EJBException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rs3 on 22.02.2017.
 */
public class LoginView extends VerticalLayout {

    private static final String BLEDNE_DANE_LOGOWANIA = "Błędne danie logowania";

    private final LoginPage loginPage;

    private Panel panel;
    private MVerticalLayout loginLayout;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginView(LoginPage loginPage) {
        this.loginPage = loginPage;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        loginLayout = new MVerticalLayout();
        panel = new Panel("Panel logowania");
        usernameField = new MTextField("Nazwa użytkownika").withWidth(300, Unit.PIXELS)
                .withValidator(new StringLengthValidator("Niepoprawna wartość pola, wymagana liczba znaków - 3", 3, 3, false));
        passwordField = new MPasswordField("Hasło").withWidth(300, Unit.PIXELS)
                .withValidator(new StringLengthValidator("Niepoprawna wartość pola", 1, 80, false));
        loginButton = new MButton(FontAwesome.SIGN_IN).withCaption("Zaloguj").withStyleName(ValoTheme.BUTTON_SMALL);
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        setSizeFull();
        setMargin(true);
        setSpacing(true);
    }

    private void initLayout() {
        loginLayout.setHeight(100, Unit.PERCENTAGE);
        loginLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        loginLayout.addComponents(usernameField, passwordField, loginButton);
        loginLayout.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);
        usernameField.focus();
        panel.setSizeUndefined();
        panel.setContent(loginLayout);
        addComponent(panel);

        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private void addListeners() {
        loginButton.addClickListener(clickEvent -> {
            try {
                Uzytkownik uzytkownik = loginPage.getPresenter().getUzytkownikOnLogIn(usernameField.getValue(), PasswordProvider.hashPassword(passwordField.getValue()));

                if (uzytkownik != null && uzytkownik.getZablokowany().getDatabaseValue().equalsIgnoreCase(UzytkownikZablokowanyEnum.NIE_LOW.getDatabaseValue()))
                    loginPage.getPresenter().onLoginButtonPressed(uzytkownik);
                else if (uzytkownik != null && uzytkownik.getZablokowany().getDatabaseValue().equalsIgnoreCase(UzytkownikZablokowanyEnum.TAK_LOW.getDatabaseValue()))
                    Notification.show("Użytkownik " + usernameField.getValue() + " jest zablokowany. Skontaktuj się z administratorem.", Notification.Type.WARNING_MESSAGE);
                else
                    Notification.show(BLEDNE_DANE_LOGOWANIA, Notification.Type.ERROR_MESSAGE);
            } catch (EJBException | NoSuchAlgorithmException e) {
                Notification.show(BLEDNE_DANE_LOGOWANIA, Notification.Type.ERROR_MESSAGE);
            }
        });
    }
}
