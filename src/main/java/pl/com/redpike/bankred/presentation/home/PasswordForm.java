package pl.com.redpike.bankred.presentation.home;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.UserError;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.form.AbstractForm;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.security.PasswordProvider;
import pl.com.redpike.bankred.util.properties.UzytkownikPropertyUtil;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Redpike
 */
public class PasswordForm extends AbstractForm<Uzytkownik> {

    private final HomeEditLoggedUserWindow homeEditLoggedUserWindow;
    private Uzytkownik uzytkownik;
    private FormLayout formLayout;
    private PasswordField oldPassowrdField;
    private PasswordField confirmPasswordField;

    @PropertyId(UzytkownikPropertyUtil.HASLO)
    private PasswordField passwordField;

    public PasswordForm(HomeEditLoggedUserWindow homeEditLoggedUserWindow, Uzytkownik uzytkownik) {
        this.homeEditLoggedUserWindow = homeEditLoggedUserWindow;
        this.uzytkownik = uzytkownik;

        initComponents();
        initLayout();
        addListeners();
        addValidators();
    }

    private void initComponents() {
        oldPassowrdField = new MPasswordField("Aktualne hasło");
        passwordField = new MPasswordField("Nowe hasło");
        confirmPasswordField = new MPasswordField("Powtórz nowe hasło");

        formLayout = new FormLayout(oldPassowrdField, passwordField, confirmPasswordField);

        setImmediate(true);
        setCompositionRoot(formLayout);
    }

    private void initLayout() {
        formLayout.forEach(component -> component.setWidth(300, Unit.PIXELS));
        formLayout.setSizeFull();
    }

    private void addListeners() {
        confirmPasswordField.addValueChangeListener(event -> {
            if (confirmPasswordField.getValue() != null && !confirmPasswordField.getValue().equals(passwordField.getValue())) {
                passwordField.setComponentError(new UserError("Wprowadzone hasła nie są takie same"));
                confirmPasswordField.setComponentError(new UserError("Wprowadzone hasła nie są takie same"));
            } else {
                passwordField.setComponentError(null);
                confirmPasswordField.setComponentError(null);
            }
        });
    }

    private void addValidators() {
        setEagerValidation(true);

        oldPassowrdField.addValidator(new StringLengthValidator("Niepoprawne haslo", 1, 80, false));
        passwordField.addValidator(new StringLengthValidator("Podane hasło ma niepoprawną długość", 8, 80, false));
        confirmPasswordField.addValidator(new StringLengthValidator("Podane hasło ma niepoprawną długość", 8, 80, false));
    }

    public boolean isValid() {
        try {
            return isFieldValidated();
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    private boolean isFieldValidated() throws NoSuchAlgorithmException {
        if (isOldPasswordGood() && passwordField.isValid() && confirmPasswordField.isValid()) {
            Uzytkownik uzytkownik = editUzytkownik();
            setEntity(uzytkownik);
            return true;
        } else
            return false;
    }

    private boolean isOldPasswordGood() throws NoSuchAlgorithmException {
        if (PasswordProvider.hashPassword(oldPassowrdField.getValue()).equals(uzytkownik.getHaslo())) {
            oldPassowrdField.setComponentError(null);
            return true;
        } else {
            oldPassowrdField.setComponentError(new UserError("Podane hasło jest niepoprawne"));
            return false;
        }
    }

    private Uzytkownik editUzytkownik() throws NoSuchAlgorithmException {
        uzytkownik.setHaslo(PasswordProvider.hashPassword(passwordField.getValue()));

        return uzytkownik;
    }

    @Override
    protected Component createContent() {
        return formLayout;
    }
}
