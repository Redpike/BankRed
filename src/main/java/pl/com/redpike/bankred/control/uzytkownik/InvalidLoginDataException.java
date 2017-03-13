package pl.com.redpike.bankred.control.uzytkownik;

import javax.ejb.EJBException;

/**
 * Created by Redpike
 */
public class InvalidLoginDataException extends EJBException {

    public InvalidLoginDataException(String message) {
        super(message);
    }
}
