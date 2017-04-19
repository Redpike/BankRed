package pl.com.redpike.bankred.control.klient;

import com.vaadin.data.util.converter.StringToDateConverter;
import pl.com.redpike.bankred.util.properties.BankRedProperites;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Redpike
 */
public class DateConverter extends StringToDateConverter {

    @Override
    public DateFormat getFormat(Locale locale) {
        return new SimpleDateFormat(BankRedProperites.DATE_FORMAT);
    }
}
