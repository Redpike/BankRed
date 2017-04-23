package pl.com.redpike.bankred.presentation.rachk;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import org.vaadin.viritin.fields.MDateField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import pl.com.redpike.bankred.business.klient.Klient;
import pl.com.redpike.bankred.business.rachk.Rachk;
import pl.com.redpike.bankred.control.rachk.RachkGenerator;
import pl.com.redpike.bankred.util.properties.BankRedProperites;
import pl.com.redpike.bankred.util.properties.RachkPropertyUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Redpike
 */
public class RachkForm extends AbstractForm<Rachk> {

    private static final String DATA_UTW_VALIDATION_MESSAGE = "Data utworzenia rachunku nie może być późniejsza od daty zamknięcia";
    private static final String DATA_ZAM_VALIDATION_MESSAGE = "Data zamknięcia rachunku nie może być wcześniejsza od daty utworzenia";

    private final RachkAddEditWindow rachkAddEditWindow;
    private Rachk rachk;
    private FormLayout formLayout;

    @PropertyId(RachkPropertyUtil.RACHUNEK)
    private TextField rachunekField;

    @PropertyId(RachkPropertyUtil.KLIENT)
    private ComboBox klientComboBox;

    @PropertyId(RachkPropertyUtil.DATA_UTWORZENIA)
    private DateField dataUtworzeniaField;

    @PropertyId(RachkPropertyUtil.DATA_ZAMKNIECIA)
    private DateField dataZamknieciaField;


    public RachkForm(RachkAddEditWindow rachkAddEditWindow) {
        this.rachkAddEditWindow = rachkAddEditWindow;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        rachk = new Rachk();
        rachunekField = new MTextField(RachkPropertyUtil.RACHUNEK_HEADER);
        rachunekField.setValue(RachkGenerator.generateRachk());
        klientComboBox = initKlientComboBox();
        dataUtworzeniaField = new MDateField(RachkPropertyUtil.DATA_UTWORZENIA_HEADER);
        dataUtworzeniaField.setDateFormat(BankRedProperites.DATE_FORMAT);
        dataZamknieciaField = new MDateField(RachkPropertyUtil.DATA_ZAMKNIECIA_HEADER);
        dataZamknieciaField.setDateFormat(BankRedProperites.DATE_FORMAT);

        formLayout = new FormLayout(rachunekField, klientComboBox, dataUtworzeniaField, dataZamknieciaField);

        setImmediate(true);
        setCompositionRoot(formLayout);
    }

    private void initLayout() {
        rachunekField.setReadOnly(true);

        formLayout.forEach(component -> component.setWidth(300, Unit.PIXELS));
        formLayout.setSizeFull();
    }

    private void addListeners() {
        dataUtworzeniaField.addValueChangeListener(event -> {
            if (dataUtworzeniaField.getValue().after(dataZamknieciaField.getValue()))
                dataUtworzeniaField.setComponentError(new UserError(DATA_UTW_VALIDATION_MESSAGE));
            else
                dataUtworzeniaField.setComponentError(null);
        });

        dataZamknieciaField.addValueChangeListener(event -> {
            if (dataZamknieciaField.getValue().before(dataUtworzeniaField.getValue()))
                dataZamknieciaField.setComponentError(new UserError(DATA_ZAM_VALIDATION_MESSAGE));
            else
                dataZamknieciaField.setComponentError(null);
        });
    }

    private ComboBox initKlientComboBox() {
        ComboBox klientComboBox = new ComboBox();
        klientComboBox.setCaption(RachkPropertyUtil.KLIENT_HEADER);
        klientComboBox.setNullSelectionAllowed(false);
        List<Klient> klientList = rachkAddEditWindow.getRachkPage().getRachkPresenter().findAllKlient();
        klientComboBox.addItems(klientList);
        klientList.forEach(klient -> klientComboBox.setItemCaption(klient, klient.getImie() + " " + klient.getNazwisko()));

        return klientComboBox;
    }

    public void setSelectedRachk(Rachk rachk) {
        this.rachk = rachk;
        rachunekField.setReadOnly(false);
        rachunekField.setValue(rachk.getRachunek());
        rachunekField.setReadOnly(true);
        klientComboBox.setValue(rachk.getKlient());
        dataUtworzeniaField.setValue(rachk.getDataUtworzenia());
        dataZamknieciaField.setValue(rachk.getDataZamkniecia());
    }

    @Override
    public boolean isValid() {
        if (dataUtworzeniaField.isValid() && dataZamknieciaField.isValid()) {
            Rachk rachk = createRachk();
            setEntity(rachk);
            return true;
        } else
            return false;
    }

    private Rachk createRachk() {
        rachk.setRachunek(rachunekField.getValue());
        rachk.setKlient((Klient) klientComboBox.getValue());
        rachk.setDataUtworzenia(dataUtworzeniaField.getValue());
        rachk.setDataZamkniecia(dataZamknieciaField.getValue());
        rachk.setStanKonta(BigDecimal.ZERO);

        return rachk;
    }


    @Override
    protected Component createContent() {
        return formLayout;
    }
}
