package pl.com.redpike.bankred.business.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Redpike
 */
public enum PlecEnum {

    KOBIETA_UPP("K", "Kobieta"),
    KOBIETA_LOW("k", "Kobieta"),
    MEZCZYZNA_UPP("M", "Mężczyzna"),
    MEZCZYZNA_LOW("m", "Mężczyzna");

    private String databaseValue;
    private String description;

    PlecEnum(String databaseValue, String description) {
        this.databaseValue = databaseValue;
        this.description = description;
    }

    public String getDatabaseValue() {
        return databaseValue;
    }

    public String getDescription() {
        return description;
    }

    public static PlecEnum getEnum(String value) {
        if (value.equalsIgnoreCase(KOBIETA_LOW.getDatabaseValue()))
            return KOBIETA_UPP;
        if (value.equalsIgnoreCase(MEZCZYZNA_LOW.getDatabaseValue()))
            return MEZCZYZNA_UPP;

        throw new IllegalArgumentException();
    }

    public static List<PlecEnum> getEnumsForComboBox() {
        List<PlecEnum> plecEnums = new ArrayList<>();
        plecEnums.add(MEZCZYZNA_UPP);
        plecEnums.add(KOBIETA_UPP);

        return plecEnums;
    }
}
