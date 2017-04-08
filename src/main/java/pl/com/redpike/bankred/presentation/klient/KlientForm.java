package pl.com.redpike.bankred.presentation.klient;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MDateField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import pl.com.redpike.bankred.business.enums.PlecEnum;
import pl.com.redpike.bankred.business.klient.Klient;
import pl.com.redpike.bankred.util.properties.KlientPropertyUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Redpike
 */
public class KlientForm extends AbstractForm<Klient> {

    private final KlientAddEditWindow klientAddEditWindow;
    private Klient klient;
    private FormLayout formLayout;
    private ComboBox peselRegonComboBox;
    private Button adresButton;

    @PropertyId(KlientPropertyUtil.MODULO)
    private TextField moduloField;

    @PropertyId(KlientPropertyUtil.PESEL)
    private TextField peselField;

    @PropertyId(KlientPropertyUtil.REGON)
    private TextField regonField;

    @PropertyId(KlientPropertyUtil.IMIE)
    private TextField imieField;

    @PropertyId(KlientPropertyUtil.IMIE2)
    private TextField imie2Field;

    @PropertyId(KlientPropertyUtil.NAZWISKO)
    private TextField nazwiskoField;

    @PropertyId(KlientPropertyUtil.DATA_URODZENIA)
    private DateField dataUrodzeniaField;

    @PropertyId(KlientPropertyUtil.PLEC)
    private ComboBox plecComboBox;

    public KlientForm(KlientAddEditWindow klientAddEditWindow) {
        this.klientAddEditWindow = klientAddEditWindow;

        initComponents();
        initLayout();
        addListeners();
        addValidators();
    }

    private void initComponents() {
        initPeselRegonComboBox();
        klient = new Klient();
        moduloField = new MTextField("Modulo");
        peselField = new MTextField("PESEL");
        regonField = new MTextField("REGON");
        imieField = new MTextField("Imię");
        imie2Field = new MTextField("Drugie imię");
        nazwiskoField = new MTextField("Nazwisko");
        dataUrodzeniaField = new MDateField("Data urodzenia");
        initPlecComboBox();
        adresButton = new MButton("Adres").withIcon(FontAwesome.HOME).withStyleName(ValoTheme.BUTTON_SMALL);

        formLayout = new FormLayout(peselRegonComboBox, moduloField, peselField, regonField, imieField, imie2Field, nazwiskoField,
                dataUrodzeniaField, plecComboBox, adresButton);

        setImmediate(true);
        setCompositionRoot(formLayout);
    }

    private void initPeselRegonComboBox() {
        peselRegonComboBox = new ComboBox();
        peselRegonComboBox.setCaption("Rodzaj klienta");
        peselRegonComboBox.setImmediate(true);
        peselRegonComboBox.setNullSelectionAllowed(false);
        peselRegonComboBox.addItems("PESEL", "REGON");
    }

    private void initPlecComboBox() {
        plecComboBox = new ComboBox();
        plecComboBox.setCaption("Płeć");
        plecComboBox.setNullSelectionAllowed(false);
        List<PlecEnum> plecList = PlecEnum.getEnumsForComboBox();
        plecComboBox.addItems(plecList);
        plecComboBox.setValue(plecList.iterator().next());
        plecList.forEach(plec -> plecComboBox.setItemCaption(plec, plec.getDescription()));
    }

    private void initLayout() {
        peselField.setVisible(false);
        regonField.setVisible(false);

        formLayout.forEach(component -> component.setWidth(300, Unit.PIXELS));
        adresButton.setWidth(100, Unit.PIXELS);
        formLayout.setSizeFull();
    }

    private void addListeners() {
        peselRegonComboBox.addValueChangeListener(event -> {
            peselField.setVisible(peselRegonComboBox.getValue().equals("PESEL"));
            regonField.setVisible(peselRegonComboBox.getValue().equals("REGON"));
        });
    }

    private void addValidators() {

    }

    public void setSelectedKlient(Klient klient) {
        this.klient = klient;
        peselRegonComboBox.setVisible(false);
        moduloField.setValue(String.valueOf(klient.getModulo()));
        peselField.setValue(klient.getPesel());
        regonField.setValue(klient.getRegon());
        imieField.setValue(klient.getImie());
        imie2Field.setValue(klient.getImie2());
        nazwiskoField.setValue(klient.getNazwisko());
        dataUrodzeniaField.setValue(klient.getDataUrodzenia());
        plecComboBox.setValue(klient.getPlec());
    }

    public boolean isValid() {
        if (moduloField.isValid()) {
            Klient klient = createKlient();
            setEntity(klient);
            return true;
        } else
            return false;
    }

    private Klient createKlient() {
        klient.setModulo(new BigDecimal(moduloField.getValue()));
        klient.setPesel(peselField.getValue());
        klient.setRegon(regonField.getValue());
        klient.setImie(imieField.getValue());
        klient.setImie2(imie2Field.getValue());
        klient.setNazwisko(nazwiskoField.getValue());
        klient.setDataUrodzenia(dataUrodzeniaField.getValue());
        klient.setPlec((PlecEnum) plecComboBox.getValue());
//        klient.setAdres();

        return klient;
    }

    @Override
    protected Component createContent() {
        return formLayout;
    }
}
