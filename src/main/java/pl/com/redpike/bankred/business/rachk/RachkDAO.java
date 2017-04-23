package pl.com.redpike.bankred.business.rachk;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Redpike
 */
@Local
public interface RachkDAO {

    List<Rachk> findAll();

    void addRachunek(Rachk rachk);

    void editRachunek(Rachk rachk);

    void removeRachunek(Rachk rachk);
}
