package pl.com.redpike.bankred.presentation.rola;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.util.properties.RolaPropertyUtil;

/**
 * Created by rs3 on 14.03.2017.
 */
public class RolaForm extends AbstractForm<Rola> {

    private final RolaAddEditWindow rolaAddEditWindow;
    private FormLayout formLayout;
    private Rola rola;

    @PropertyId(RolaPropertyUtil.NAZWA)
    private TextField nameField;

    public RolaForm(RolaAddEditWindow rolaAddEditWindow) {
        this.rolaAddEditWindow = rolaAddEditWindow;

        initComponents();
        initLayout();
        addValidators();
    }

    private void initComponents() {
        rola = new Rola();

        nameField = new MTextField("Nazwa roli");

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

        nameField.addValidator(new StringLengthValidator("Niepoprawna nazwa roli", 1, 30, false));
    }

    public void setSelectedRola(Rola rola) {
        this.rola = rola;
        nameField.setValue(rola.getNazwa());
    }

    @Override
    public boolean isValid() {
        return isFieldValidated();
    }

    private boolean isFieldValidated() {
        if (nameField.isValid()) {
            rola.setNazwa(nameField.getValue());
            setEntity(rola);
            return true;
        } else
            return false;
    }

    @Override
    protected Component createContent() {
        return formLayout;
    }
}
