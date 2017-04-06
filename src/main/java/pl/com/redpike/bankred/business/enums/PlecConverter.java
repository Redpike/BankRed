package pl.com.redpike.bankred.business.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Redpike
 */
@Converter
public class PlecConverter implements AttributeConverter<PlecEnum, String> {

    @Override
    public String convertToDatabaseColumn(PlecEnum plecEnum) {
        return plecEnum.getDatabaseValue();
    }

    @Override
    public PlecEnum convertToEntityAttribute(String s) {
        return PlecEnum.getEnum(s);
    }
}
