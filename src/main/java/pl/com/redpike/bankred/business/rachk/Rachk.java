package pl.com.redpike.bankred.business.rachk;

import pl.com.redpike.bankred.business.klient.Klient;
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
@Table(name = "rachk", schema = BankRedProperites.BANKRED_SCHEMA)
public class Rachk {

    @Id
    @NotNull
    @Size(max = 15)
    @Column(name = "rach", length = 15, unique = true, nullable = false)
    private String rachunek;

    @OneToOne
    @JoinColumn(name = "modulo")
    private Klient klient;

    @Column(name = "data_utw")
    private Date dataUtworzenia;

    @Column(name = "data_zam")
    private Date dataZamkniecia;

    @Column(name = "stan", precision = 15, scale = 2)
    private BigDecimal stanKonta;

    public String getRachunek() {
        return rachunek;
    }

    public void setRachunek(String rachunek) {
        this.rachunek = rachunek;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Date getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(Date dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public Date getDataZamkniecia() {
        return dataZamkniecia;
    }

    public void setDataZamkniecia(Date dataZamkniecia) {
        this.dataZamkniecia = dataZamkniecia;
    }

    public BigDecimal getStanKonta() {
        return stanKonta;
    }

    public void setStanKonta(BigDecimal stanKonta) {
        this.stanKonta = stanKonta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rachk rachk = (Rachk) o;

        if (rachunek != null ? !rachunek.equals(rachk.rachunek) : rachk.rachunek != null) return false;
        if (klient != null ? !klient.equals(rachk.klient) : rachk.klient != null) return false;
        if (dataUtworzenia != null ? !dataUtworzenia.equals(rachk.dataUtworzenia) : rachk.dataUtworzenia != null)
            return false;
        if (dataZamkniecia != null ? !dataZamkniecia.equals(rachk.dataZamkniecia) : rachk.dataZamkniecia != null)
            return false;
        return stanKonta != null ? stanKonta.equals(rachk.stanKonta) : rachk.stanKonta == null;
    }

    @Override
    public int hashCode() {
        int result = rachunek != null ? rachunek.hashCode() : 0;
        result = 31 * result + (klient != null ? klient.hashCode() : 0);
        result = 31 * result + (dataUtworzenia != null ? dataUtworzenia.hashCode() : 0);
        result = 31 * result + (dataZamkniecia != null ? dataZamkniecia.hashCode() : 0);
        result = 31 * result + (stanKonta != null ? stanKonta.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rachk{" +
                "rachunek='" + rachunek + '\'' +
                ", klient=" + klient +
                ", dataUtworzenia=" + dataUtworzenia +
                ", dataZamkniecia=" + dataZamkniecia +
                ", stanKonta=" + stanKonta +
                '}';
    }
}
