package pl.com.redpike.bankred.business.rola;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by Redpike
 */
@Entity
@SequenceGenerator(name = "rola_seq", sequenceName = "rola_seq")
@Table(name = "rola", schema = "bank")
public class Rola {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rola_seq")
    @Column(name = "rola_id", precision = 3, nullable = false, unique = true)
    private BigDecimal id;

    @Size(max = 30)
    @Column(name = "nazwa", length = 30)
    private String nazwa;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rola rola = (Rola) o;

        if (id != null ? !id.equals(rola.id) : rola.id != null) return false;
        return nazwa != null ? nazwa.equals(rola.nazwa) : rola.nazwa == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return nazwa;
    }
}
