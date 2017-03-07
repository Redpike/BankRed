package pl.com.redpike.bankred.presentation.login;

import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.enums.UzytkownikZablokowanyEnum;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.security.SHAProvider;

import javax.ejb.EJBException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rs3 on 22.02.2017.
 */
public class LoginPage extends VerticalLayout {

    private final LoginView loginView;

    private Panel panel;
    private MVerticalLayout loginLayout;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginPage(LoginView loginView) {
        this.loginView = loginView;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        loginLayout = new MVerticalLayout();
        panel = new Panel("Panel logowania");
        usernameField = new MTextField("Nazwa użytkownika").withWidth(300, Unit.PIXELS)
                .withValidator(new StringLengthValidator("Niepoprawna wartość pola, wymagana liczba znaków - 3", 0, 3, true));
        passwordField = new MPasswordField("Hasło").withWidth(300, Unit.PIXELS)
                .withValidator(new StringLengthValidator("Niepoprawna wartość pola", 0, 80, true));
        loginButton = new MButton(FontAwesome.SIGN_IN).withCaption("Zaloguj");

        setSizeFull();
        setMargin(true);
        setSpacing(true);
    }

    private void initLayout() {
        loginLayout.setHeight(100, Unit.PERCENTAGE);
        loginLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        loginLayout.addComponents(usernameField, passwordField, loginButton);
        loginLayout.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);
        panel.setSizeUndefined();
        panel.setContent(loginLayout);
        addComponent(panel);

        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private void addListeners() {
        loginButton.addClickListener(clickEvent -> {
            try {
                Uzytkownik uzytkownik = loginView.getPresenter().getUzytkownikOnLogIn(usernameField.getValue(), SHAProvider.hashPassword(passwordField.getValue()));

                if (uzytkownik != null && uzytkownik.getZablokowany().getDatabaseValue().equalsIgnoreCase(UzytkownikZablokowanyEnum.NIE_LOW.getDatabaseValue()))
                    loginView.getPresenter().onLoginButtonPressed(uzytkownik.getImie(), uzytkownik.getNazwisko());
                else if (uzytkownik != null && uzytkownik.getZablokowany().getDatabaseValue().equalsIgnoreCase(UzytkownikZablokowanyEnum.TAK_LOW.getDatabaseValue()))
                    Notification.show("Użytkownik " + usernameField.getValue() + " jest zablokowany. Skontaktuj się z administratorem.", Notification.Type.WARNING_MESSAGE);
                else
                    Notification.show("Błędne dane logowania", Notification.Type.ERROR_MESSAGE);
            } catch (EJBException | NoSuchAlgorithmException e) {
                Notification.show("Błędne dane logowania", Notification.Type.ERROR_MESSAGE);
            }
        });
    }
}
