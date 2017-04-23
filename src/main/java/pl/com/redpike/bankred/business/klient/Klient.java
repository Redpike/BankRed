package pl.com.redpike.bankred.business.klient;

import pl.com.redpike.bankred.business.adres.Adres;
import pl.com.redpike.bankred.business.enums.PlecConverter;
import pl.com.redpike.bankred.business.enums.PlecEnum;
import pl.com.redpike.bankred.util.properties.BankRedProperites;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Redpike
 */
@Entity
@Table(name = "klient", schema = BankRedProperites.BANKRED_SCHEMA)
public class Klient {

    @Id
    @NotNull
    @Column(name = "modulo", precision = 7, unique = true, nullable = false)
    private BigDecimal modulo;

    @Size(max = 11)
    @Column(name = "pesel", length = 11)
    private String pesel;

    @Size(max = 14)
    @Column(name = "regon", length = 14)
    private String regon;

    @Size(max = 30)
    @Column(name = "imie", length = 30)
    private String imie;

    @Size(max = 30)
    @Column(name = "imie2", length = 30)
    private String imie2;

    @Size(max = 80)
    @Column(name = "nazwisko", length = 30)
    private String nazwisko;

    @Column(name = "data_ur")
    private Date dataUrodzenia;

    @Column(name = "data_zal")
    private Date dataZalozenia;

    @Convert(converter = PlecConverter.class)
    @Column(name = "plec", length = 1)
    private PlecEnum plec;

    @AttributeOverrides({
            @AttributeOverride(name = "ulica", column = @Column(name = "ulica", length = 50)),
            @AttributeOverride(name = "nrDomu", column = @Column(name = "nr_domu", length = 4)),
            @AttributeOverride(name = "nrMieszkania", column = @Column(name = "nr_mieszkania", length = 5)),
            @AttributeOverride(name = "miejscowosc", column = @Column(name = "miejscowosc", length = 30)),
            @AttributeOverride(name = "kodPocztowy", column = @Column(name = "kod_pocztowy", length = 6)),
    })
    private Adres adres;

    public BigDecimal getModulo() {
        return modulo;
    }

    public void setModulo(BigDecimal modulo) {
        this.modulo = modulo;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getImie2() {
        return imie2;
    }

    public void setImie2(String imie2) {
        this.imie2 = imie2;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public Date getDataZalozenia() {
        return dataZalozenia;
    }

    public void setDataZalozenia(Date dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    public PlecEnum getPlec() {
        return plec;
    }

    public void setPlec(PlecEnum plec) {
        this.plec = plec;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Klient klient = (Klient) o;

        if (modulo != null ? !modulo.equals(klient.modulo) : klient.modulo != null) return false;
        if (pesel != null ? !pesel.equals(klient.pesel) : klient.pesel != null) return false;
        if (regon != null ? !regon.equals(klient.regon) : klient.regon != null) return false;
        if (imie != null ? !imie.equals(klient.imie) : klient.imie != null) return false;
        if (imie2 != null ? !imie2.equals(klient.imie2) : klient.imie2 != null) return false;
        if (nazwisko != null ? !nazwisko.equals(klient.nazwisko) : klient.nazwisko != null) return false;
        if (dataUrodzenia != null ? !dataUrodzenia.equals(klient.dataUrodzenia) : klient.dataUrodzenia != null)
            return false;
        if (dataZalozenia != null ? !dataZalozenia.equals(klient.dataZalozenia) : klient.dataZalozenia != null)
            return false;
        if (plec != klient.plec) return false;
        return adres != null ? adres.equals(klient.adres) : klient.adres == null;
    }

    @Override
    public int hashCode() {
        int result = modulo != null ? modulo.hashCode() : 0;
        result = 31 * result + (pesel != null ? pesel.hashCode() : 0);
        result = 31 * result + (regon != null ? regon.hashCode() : 0);
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (imie2 != null ? imie2.hashCode() : 0);
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (dataUrodzenia != null ? dataUrodzenia.hashCode() : 0);
        result = 31 * result + (dataZalozenia != null ? dataZalozenia.hashCode() : 0);
        result = 31 * result + (plec != null ? plec.hashCode() : 0);
        result = 31 * result + (adres != null ? adres.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }
}
