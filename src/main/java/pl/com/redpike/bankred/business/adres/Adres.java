package pl.com.redpike.bankred.business.adres;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Created by Redpike
 */
@Embeddable
public class Adres {

    private String ulica;
    private String nrDomu;
    private String nrMieszkania;
    private String miejscowosc;
    private String kodPocztowy;

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getNrMieszkania() {
        return nrMieszkania;
    }

    public void setNrMieszkania(String nrMieszkania) {
        this.nrMieszkania = nrMieszkania;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adres adres = (Adres) o;

        if (ulica != null ? !ulica.equals(adres.ulica) : adres.ulica != null) return false;
        if (nrDomu != null ? !nrDomu.equals(adres.nrDomu) : adres.nrDomu != null) return false;
        if (nrMieszkania != null ? !nrMieszkania.equals(adres.nrMieszkania) : adres.nrMieszkania != null) return false;
        if (miejscowosc != null ? !miejscowosc.equals(adres.miejscowosc) : adres.miejscowosc != null) return false;
        return kodPocztowy != null ? kodPocztowy.equals(adres.kodPocztowy) : adres.kodPocztowy == null;
    }

    @Override
    public int hashCode() {
        int result = ulica != null ? ulica.hashCode() : 0;
        result = 31 * result + (nrDomu != null ? nrDomu.hashCode() : 0);
        result = 31 * result + (nrMieszkania != null ? nrMieszkania.hashCode() : 0);
        result = 31 * result + (miejscowosc != null ? miejscowosc.hashCode() : 0);
        result = 31 * result + (kodPocztowy != null ? kodPocztowy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (Objects.isNull(nrMieszkania))
            return ulica + " " + nrDomu + ", " + kodPocztowy + " " + miejscowosc;
        else
            return ulica + " " + nrDomu + "/" + nrMieszkania + ", " + kodPocztowy + " " + miejscowosc;
    }
}
