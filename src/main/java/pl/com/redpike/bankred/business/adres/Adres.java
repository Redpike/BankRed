package pl.com.redpike.bankred.business.adres;

import pl.com.redpike.bankred.util.properties.BankRedProperites;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by Redpike
 */
@Entity
@SequenceGenerator(name = "adres_seq", sequenceName = "adres_seq", allocationSize = 1)
@Table(name = "adres", schema = BankRedProperites.BANKRED_SCHEMA)
public class Adres {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adres_seq")
    @Column(name = "id", precision = 5, unique = true, nullable = false)
    private BigDecimal id;

    @Size(max = 50)
    @Column(name = "ulica", length = 50)
    private String ulica;

    @Size(max = 5)
    @Column(name = "nr_domu", length = 5)
    private String nrDomu;

    @Size(max = 5)
    @Column(name = "nr_mieszkania", length = 5)
    private String nrMieszkania;

    @Size(max = 30)
    @Column(name = "miejscowosc", length = 30)
    private String miejscowosc;

    @Size(max = 6)
    @Column(name = "kod_p", length = 6)
    private String kodPocztowy;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

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

        if (id != null ? !id.equals(adres.id) : adres.id != null) return false;
        if (ulica != null ? !ulica.equals(adres.ulica) : adres.ulica != null) return false;
        if (nrDomu != null ? !nrDomu.equals(adres.nrDomu) : adres.nrDomu != null) return false;
        if (nrMieszkania != null ? !nrMieszkania.equals(adres.nrMieszkania) : adres.nrMieszkania != null) return false;
        if (miejscowosc != null ? !miejscowosc.equals(adres.miejscowosc) : adres.miejscowosc != null) return false;
        return kodPocztowy != null ? kodPocztowy.equals(adres.kodPocztowy) : adres.kodPocztowy == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ulica != null ? ulica.hashCode() : 0);
        result = 31 * result + (nrDomu != null ? nrDomu.hashCode() : 0);
        result = 31 * result + (nrMieszkania != null ? nrMieszkania.hashCode() : 0);
        result = 31 * result + (miejscowosc != null ? miejscowosc.hashCode() : 0);
        result = 31 * result + (kodPocztowy != null ? kodPocztowy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id=" + id +
                ", ulica='" + ulica + '\'' +
                ", nrDomu='" + nrDomu + '\'' +
                ", nrMieszkania='" + nrMieszkania + '\'' +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                '}';
    }
}
