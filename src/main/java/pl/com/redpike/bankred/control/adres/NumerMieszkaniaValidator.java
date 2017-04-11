package pl.com.redpike.bankred.control.adres;

import com.vaadin.data.validator.AbstractStringValidator;

/**
 * Created by Redpike
 */
public class NumerMieszkaniaValidator extends AbstractStringValidator {

    public NumerMieszkaniaValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void validate(Object value) throws InvalidValueException {
        if (value != null && value instanceof Integer)
            return;

        super.validate(value);
    }
}
