package pl.com.redpike.bankred.presentation.uzytkownik;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import pl.com.redpike.bankred.business.enums.UzytkownikZablokowanyEnum;
import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.security.PasswordProvider;
import pl.com.redpike.bankred.util.properties.UzytkownikPropertyUtil;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Redpike
 */
public class UzytkownikForm extends AbstractForm<Uzytkownik> {

    private final UzytkownikAddEditWindow uzytkownikAddEditWindow;
    private Uzytkownik uzytkownik;
    private FormLayout formLayout;
    private PasswordField confirmPasswordField;
    private Property.ValueChangeListener valueChangeListener;

    @PropertyId(UzytkownikPropertyUtil.NAZWA)
    private TextField usernameField;

    @PropertyId(UzytkownikPropertyUtil.HASLO)
    private PasswordField passwordField;

    @PropertyId(UzytkownikPropertyUtil.IMIE)
    private TextField imieField;

    @PropertyId(UzytkownikPropertyUtil.NAZWISKO)
    private TextField nazwiskoField;

    @PropertyId(UzytkownikPropertyUtil.ROLA)
    private ComboBox rolaComboBox;

    @PropertyId(UzytkownikPropertyUtil.ZABLOKOWANY)
    private ComboBox zablokowanyComboBox;

    public UzytkownikForm(UzytkownikAddEditWindow uzytkownikAddEditWindow) {
        this.uzytkownikAddEditWindow = uzytkownikAddEditWindow;

        initComponents();
        initLayout();
        addListeners();
        addValidators();
    }

    private void initComponents() {
        uzytkownik = new Uzytkownik();
        usernameField = new MTextField("Nazwa użytkownika");
        passwordField = new MPasswordField("Hasło");
        confirmPasswordField = new MPasswordField("Powtórz hasło");
        imieField = new MTextField("Imię");
        nazwiskoField = new MTextField("Nazwisko");
        initComboBoxes();

        formLayout = new FormLayout(usernameField, passwordField, confirmPasswordField,
                imieField, nazwiskoField, rolaComboBox, zablokowanyComboBox);

        setImmediate(true);
        setCompositionRoot(formLayout);
    }

    private void initComboBoxes() {
        initRolaComboBox();
        initZablokowanyComboBox();
    }

    private void initRolaComboBox() {
        rolaComboBox = new ComboBox();
        rolaComboBox.setCaption("Rola");
        rolaComboBox.setNullSelectionAllowed(false);
        List<Rola> rolaList = uzytkownikAddEditWindow.getUzytkownikView().getUzytkownikWindowPresenter().getRoles();
        rolaComboBox.addItems(rolaList);
        rolaComboBox.setValue(rolaList.iterator().next());
        rolaList.forEach(rola -> rolaComboBox.setItemCaption(rola, rola.getNazwa()));
    }

    private void initZablokowanyComboBox() {
        zablokowanyComboBox = new ComboBox();
        zablokowanyComboBox.setCaption("Czy zablokowany");
        zablokowanyComboBox.setNullSelectionAllowed(false);
        List<UzytkownikZablokowanyEnum> uzytkownikZablokowanyEnums = UzytkownikZablokowanyEnum.getEnumsForComboBox();
        zablokowanyComboBox.addItems(uzytkownikZablokowanyEnums);
        zablokowanyComboBox.setValue(uzytkownikZablokowanyEnums.iterator().next());
        uzytkownikZablokowanyEnums.forEach(uzytkownikZablokowanyEnum ->
                zablokowanyComboBox.setItemCaption(uzytkownikZablokowanyEnum, uzytkownikZablokowanyEnum.getDescription()));
    }

    private void initLayout() {
        formLayout.forEach(component -> component.setWidth(300, Unit.PIXELS));
        formLayout.setSizeFull();
    }

    private void addListeners() {
        List<Uzytkownik> uzytkownikList = uzytkownikAddEditWindow.getUzytkownikView().getUzytkownikPresenter().getAllUzytkownicy();

        valueChangeListener = (Property.ValueChangeListener) valueChangeEvent -> {
            usernameField.setComponentError(null);
            uzytkownikList.forEach(uzytkownik -> {
                if (uzytkownik.getNazwa().equals(usernameField.getValue()))
                    usernameField.setComponentError(new UserError("Podana nazwa użytkownika już istnieje"));
            });
        };

        usernameField.addValueChangeListener(valueChangeListener);

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

        usernameField.addValidator(new StringLengthValidator("Niepoprawna nazwa użytkownika", 3, 3, false));
        passwordField.addValidator(new StringLengthValidator("Niepoprawne hasło", 1, 80, false));
        confirmPasswordField.addValidator(new StringLengthValidator("Niepoprawne hasło", 1, 80, false));
    }

    public void setSelectedUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
        usernameField.removeValueChangeListener(valueChangeListener);
        usernameField.setValue(uzytkownik.getNazwa());
        usernameField.setEnabled(false);
        imieField.setValue(uzytkownik.getImie());
        nazwiskoField.setValue(uzytkownik.getNazwisko());
        rolaComboBox.setValue(uzytkownik.getRola());
        zablokowanyComboBox.setValue(uzytkownik.getZablokowany());
    }

    public boolean isValid() {
        try {
            return isFieldValidated();
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    private boolean isFieldValidated() throws NoSuchAlgorithmException {
        if (usernameField.isValid() && passwordField.isValid() && confirmPasswordField.isValid()) {
            Uzytkownik uzytkownik = createUzytkownik();
            setEntity(uzytkownik);
            return true;
        } else
            return false;
    }

    private Uzytkownik createUzytkownik() throws NoSuchAlgorithmException {
        uzytkownik.setNazwa(usernameField.getValue());
        uzytkownik.setHaslo(PasswordProvider.hashPassword(passwordField.getValue()));
        uzytkownik.setImie(imieField.getValue());
        uzytkownik.setNazwisko(nazwiskoField.getValue());
        uzytkownik.setRola((Rola) rolaComboBox.getValue());
        uzytkownik.setZablokowany((UzytkownikZablokowanyEnum) zablokowanyComboBox.getValue());

        return uzytkownik;
    }

    @Override
    protected Component createContent() {
        return formLayout;
    }
}
