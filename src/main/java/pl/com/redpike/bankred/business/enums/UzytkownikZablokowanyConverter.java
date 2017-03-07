package pl.com.redpike.bankred.business.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Redpike
 */
@Converter
public class UzytkownikZablokowanyConverter implements AttributeConverter<UzytkownikZablokowanyEnum, String> {

    @Override
    public String convertToDatabaseColumn(UzytkownikZablokowanyEnum uzytkownikZablokowanyEnum) {
        return uzytkownikZablokowanyEnum.getDatabaseValue();
    }

    @Override
    public UzytkownikZablokowanyEnum convertToEntityAttribute(String value) {
        return UzytkownikZablokowanyEnum.getEnum(value);
    }
}
