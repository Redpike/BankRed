package pl.com.redpike.bankred.business.uzytkownik;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by rs3 on 21.02.2017.
 */
@Entity
@SequenceGenerator(name = "uzytkownik_seq", sequenceName = "uzytkownik_seq")
@Table(name = "uzytkownik", schema = "bank")
public class Uzytkownik {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uzytkownik_seq")
    @Column(name = "id", precision = 10, nullable = false, unique = true)
    private BigDecimal id;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "nazwa", length = 3, nullable = false)
    private String nazwa;

    @NotNull
    @Size(max = 80)
    @Column(name = "haslo", length = 80, nullable = false)
    private String haslo;

    @Size(max = 80)
    @Column(name = "imie", length = 80)
    private String imie;

    @Size(max = 80)
    @Column(name = "nazwisko", length = 80)
    private String nazwisko;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Uzytkownik that = (Uzytkownik) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;
        if (haslo != null ? !haslo.equals(that.haslo) : that.haslo != null) return false;
        if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;
        return nazwisko != null ? nazwisko.equals(that.nazwisko) : that.nazwisko == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", haslo='" + haslo + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
