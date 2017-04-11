package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import pl.com.redpike.bankred.business.uprawnienie.Uprawnienie;
import pl.com.redpike.bankred.util.properties.UprawnieniePropertyUtil;

/**
 * Created by Redpike
 */
public class UprawnienieForm extends AbstractForm<Uprawnienie> {

    private final UprawnienieAddEditWindow uprawnienieAddEditWindow;
    private FormLayout formLayout;
    private Uprawnienie uprawnienie;

    @PropertyId(UprawnieniePropertyUtil.NAZWA)
    private TextField nameField;

    public UprawnienieForm(UprawnienieAddEditWindow uprawnienieAddEditWindow) {
        this.uprawnienieAddEditWindow = uprawnienieAddEditWindow;

        initComponents();
        initLayout();
        addValidators();
    }

    private void initComponents() {
        uprawnienie = new Uprawnienie();

        nameField = new MTextField(UprawnieniePropertyUtil.NAZWA_HEADER);

        formLayout = new FormLayout(nameField);

        setImmediate(true);
        setCompositionRoot(formLayout);
    }

    private void initLayout() {
        formLayout.forEach(component -> component.setWidth(300, Unit.PIXELS));
        formLayout.setSizeFull();
    }

    private void addValidators() {
        setEagerValidation(true);

        nameField.addValidator(new StringLengthValidator("Niepoprawna nazwa uprawnienia", 1, 30, false));
    }

    public void setSelectedUprawnienie(Uprawnienie uprawnienie) {
        this.uprawnienie = uprawnienie;
        nameField.setValue(uprawnienie.getNazwa());
    }

    @Override
    public boolean isValid() {
        return isFieldValidated();
    }

    private boolean isFieldValidated() {
        if (nameField.isValid()) {
            uprawnienie.setNazwa(nameField.getValue());
            setEntity(uprawnienie);
            return true;
        } else
            return false;
    }

    @Override
    protected Component createContent() {
        return formLayout;
    }
}
