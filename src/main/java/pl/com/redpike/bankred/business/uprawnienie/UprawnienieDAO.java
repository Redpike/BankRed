package pl.com.redpike.bankred.business.uprawnienie;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Redpike
 */
@Local
public interface UprawnienieDAO {

    List<Uprawnienie> getAllUprawnienia();

    void addUprawnienie(Uprawnienie uprawnienie);

    void editUprawnienie(Uprawnienie uprawnienie);

    void removeUprawnienie(Uprawnienie uprawnienie);
}
