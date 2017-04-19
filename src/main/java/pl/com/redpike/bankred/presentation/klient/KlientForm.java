package pl.com.redpike.bankred.presentation.klient;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.*;
import org.vaadin.viritin.fields.MDateField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import pl.com.redpike.bankred.business.enums.PlecEnum;
import pl.com.redpike.bankred.business.klient.Klient;
import pl.com.redpike.bankred.control.klient.ModuloGenerator;
import pl.com.redpike.bankred.util.properties.BankRedProperites;
import pl.com.redpike.bankred.util.properties.KlientPropertyUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Redpike
 */
public class KlientForm extends AbstractForm<Klient> {

    private final KlientAddEditWindow klientAddEditWindow;
    private Klient klient;
    private FormLayout formLayout;
    private ComboBox peselRegonComboBox;

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

    @PropertyId(KlientPropertyUtil.DATA_ZALOZENIA)
    private DateField dataZalozeniaField;

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
        moduloField = new MTextField(KlientPropertyUtil.MODULO_HEADER);
        moduloField.setValue(String.valueOf(ModuloGenerator.generateModulo()));
        peselField = new MTextField(KlientPropertyUtil.PESEL_HEADER);
        regonField = new MTextField(KlientPropertyUtil.REGON_HEADER);
        imieField = new MTextField(KlientPropertyUtil.IMIE_HEADER);
        imie2Field = new MTextField(KlientPropertyUtil.IMIE2_HEADER);
        nazwiskoField = new MTextField(KlientPropertyUtil.NAZWISKO_HEADER);
        dataUrodzeniaField = new MDateField(KlientPropertyUtil.DATA_URODZENIA_HEADER);
        dataUrodzeniaField.setValue(new Date());
        dataUrodzeniaField.setDateFormat(BankRedProperites.DATE_FORMAT);
        dataZalozeniaField = new MDateField(KlientPropertyUtil.DATA_ZALOZENIA_HEADER);
        dataZalozeniaField.setValue(new Date());
        dataZalozeniaField.setDateFormat(BankRedProperites.DATE_FORMAT);
        initPlecComboBox();

        formLayout = new FormLayout(peselRegonComboBox, moduloField, peselField, regonField, imieField, imie2Field, nazwiskoField,
                dataUrodzeniaField, dataZalozeniaField, plecComboBox);

        setImmediate(true);
        setCompositionRoot(formLayout);
    }

    private void initPeselRegonComboBox() {
        peselRegonComboBox = new ComboBox();
        peselRegonComboBox.setCaption("Rodzaj klienta");
        peselRegonComboBox.setImmediate(true);
        peselRegonComboBox.setNullSelectionAllowed(false);
        peselRegonComboBox.addItems(KlientPropertyUtil.PESEL_HEADER, KlientPropertyUtil.REGON_HEADER);
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
        moduloField.setReadOnly(true);
        peselField.setVisible(false);
        regonField.setVisible(false);
        dataUrodzeniaField.setVisible(false);
        dataZalozeniaField.setVisible(false);

        formLayout.forEach(component -> component.setWidth(300, Unit.PIXELS));
        formLayout.setSizeFull();
    }

    private void addListeners() {
        peselRegonComboBox.addValueChangeListener(event -> {
            peselField.setVisible(peselRegonComboBox.getValue().equals(KlientPropertyUtil.PESEL_HEADER));
            regonField.setVisible(peselRegonComboBox.getValue().equals(KlientPropertyUtil.REGON_HEADER));
            plecComboBox.setVisible(peselRegonComboBox.getValue().equals(KlientPropertyUtil.PESEL_HEADER));
            dataUrodzeniaField.setVisible(peselRegonComboBox.getValue().equals(KlientPropertyUtil.PESEL_HEADER));
            dataZalozeniaField.setVisible(peselRegonComboBox.getValue().equals(KlientPropertyUtil.REGON_HEADER));
        });
    }

    private void addValidators() {
        peselRegonComboBox.addValidator(new NullValidator("Proszę wybrać jedną z dostępnych opcji", false));
    }

    public void setSelectedKlient(Klient klient) {
        this.klient = klient;
        peselRegonComboBox.setVisible(false);
        moduloField.setReadOnly(false);
        moduloField.setValue(String.valueOf(klient.getModulo()));
        moduloField.setReadOnly(true);
        regonField.setValue(klient.getRegon());
        imieField.setValue(klient.getImie());
        imie2Field.setValue(klient.getImie2());
        nazwiskoField.setValue(klient.getNazwisko());
        dataUrodzeniaField.setValue(klient.getDataUrodzenia());
        dataZalozeniaField.setValue(klient.getDataZalozenia());
        plecComboBox.setValue(klient.getPlec());

        chechKlientType();
    }

    private void chechKlientType() {
        if (Objects.nonNull(klient.getPesel()))
            prepareFormForOsobyFizycznej();
        else
            prepareFormForPodmiotuGospodarczego();
    }

    private void prepareFormForOsobyFizycznej() {
        peselField.setVisible(true);
        peselField.setValue(klient.getPesel());
        plecComboBox.setVisible(true);
        regonField.setVisible(false);
        dataUrodzeniaField.setVisible(true);
        dataZalozeniaField.setVisible(false);
    }

    private void prepareFormForPodmiotuGospodarczego() {
        peselField.setVisible(false);
        plecComboBox.setVisible(false);
        regonField.setVisible(true);
        regonField.setValue(klient.getRegon());
        dataUrodzeniaField.setVisible(false);
        dataZalozeniaField.setVisible(true);
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

        if (Objects.nonNull(klient.getPesel())) {
            klient.setPlec((PlecEnum) plecComboBox.getValue());
            klient.setDataUrodzenia(dataUrodzeniaField.getValue());
        } else
            klient.setDataZalozenia(dataZalozeniaField.getValue());

        return klient;
    }

    @Override
    protected Component createContent() {
        return formLayout;
    }
}
