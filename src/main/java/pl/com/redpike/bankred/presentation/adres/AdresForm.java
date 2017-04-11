package pl.com.redpike.bankred.presentation.adres;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import pl.com.redpike.bankred.business.adres.Adres;
import pl.com.redpike.bankred.control.adres.KodPocztowyValidator;
import pl.com.redpike.bankred.control.adres.NumerMieszkaniaValidator;
import pl.com.redpike.bankred.util.properties.AdresPropertyUtil;

/**
 * Created by Redpike
 */
public class AdresForm extends AbstractForm<Adres>{

    private Adres adres;
    private FormLayout formLayout;

    @PropertyId(AdresPropertyUtil.ULICA)
    private TextField ulicaField;

    @PropertyId(AdresPropertyUtil.NR_DOMU)
    private TextField nrDomuField;

    @PropertyId(AdresPropertyUtil.NR_MIESZKANIA)
    private TextField nrMieszkaniaField;

    @PropertyId(AdresPropertyUtil.MIEJSCOWOSC)
    private TextField miejscowoscField;

    @PropertyId(AdresPropertyUtil.KOD_POCZTOWY)
    private TextField kodPocztowyField;

    public AdresForm() {
        initComponents();
        initLayout();
        addValidators();
    }

    private void initComponents() {
        adres = new Adres();
        ulicaField = new MTextField(AdresPropertyUtil.ULICA_HEADER);
        nrDomuField = new MTextField(AdresPropertyUtil.NR_DOMU_HEADER);
        nrMieszkaniaField = new MTextField(AdresPropertyUtil.NR_MIESZKANIA_HEADER);
        miejscowoscField = new MTextField(AdresPropertyUtil.MIEJSCOWOSC_HEADER);
        kodPocztowyField = new MTextField(AdresPropertyUtil.KOD_POCZTOWY_HEADER);

        formLayout = new FormLayout(ulicaField, nrDomuField, nrMieszkaniaField, miejscowoscField, kodPocztowyField);

        setImmediate(true);
        setCompositionRoot(formLayout);
    }

    private void initLayout() {
        formLayout.forEach(component -> component.setWidth(300, Unit.PIXELS));
        formLayout.setSizeFull();
    }

    private void addValidators() {
        nrMieszkaniaField.addValidator(new NumerMieszkaniaValidator("Niepoprawny format danych (wymagana wartość liczbowa)"));
        kodPocztowyField.addValidator(new KodPocztowyValidator(AdresPropertyUtil.KOD_POCZTOWY_REGEXP,"Niepoprawny format kodu pocztowego"));
    }

    public void setSelectedAdres(Adres adres) {
        this.adres = adres;
        ulicaField.setValue(this.adres.getUlica());
        nrDomuField.setValue(this.adres.getNrDomu());
        nrMieszkaniaField.setValue(this.adres.getNrMieszkania());
        miejscowoscField.setValue(this.adres.getMiejscowosc());
        kodPocztowyField.setValue(this.adres.getKodPocztowy());
    }

    public boolean isValid() {
        if (nrMieszkaniaField.isValid() && kodPocztowyField.isValid()) {
            Adres adres = createAdres();
            setEntity(adres);
            return true;
        } else
            return false;
    }

    private Adres createAdres() {
        adres.setUlica(ulicaField.getValue());
        adres.setNrDomu(nrDomuField.getValue());
        adres.setNrMieszkania(nrMieszkaniaField.getValue());
        adres.setMiejscowosc(miejscowoscField.getValue());
        adres.setKodPocztowy(kodPocztowyField.getValue());

        return adres;
    }

    @Override
    protected Component createContent() {
        return formLayout;
    }
}
