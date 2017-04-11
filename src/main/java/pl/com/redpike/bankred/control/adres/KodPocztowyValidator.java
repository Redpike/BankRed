package pl.com.redpike.bankred.control.adres;

import com.vaadin.data.validator.RegexpValidator;

/**
 * Created by Redpike
 */
public class KodPocztowyValidator extends RegexpValidator {

    public KodPocztowyValidator(String regexp, String errorMessage) {
        super(regexp, errorMessage);
    }
}
