package pl.com.redpike.bankred.business.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Redpike
 */
public enum UzytkownikZablokowanyEnum {

    TAK_UPP("T", "Tak"),
    TAK_LOW("t", "Tak"),
    NIE_UPP("N", "Nie"),
    NIE_LOW("n", "Nie");

    private String databaseValue;
    private String description;

    UzytkownikZablokowanyEnum(String databaseValue, String description) {
        this.databaseValue = databaseValue;
        this.description = description;
    }

    public String getDatabaseValue() {
        return databaseValue;
    }

    public String getDescription() {
        return description;
    }

    public static UzytkownikZablokowanyEnum getEnum(String value) {
        if (value.equalsIgnoreCase(TAK_LOW.getDatabaseValue()))
            return TAK_UPP;
        if (value.equalsIgnoreCase(NIE_LOW.getDatabaseValue()))
            return NIE_UPP;

        throw new IllegalArgumentException();
    }

    public static List<UzytkownikZablokowanyEnum> getEnumsForComboBox() {
        List<UzytkownikZablokowanyEnum> uzytkownikZablokowanyEnums = new ArrayList<>();
        uzytkownikZablokowanyEnums.add(NIE_UPP);
        uzytkownikZablokowanyEnums.add(TAK_UPP);

        return uzytkownikZablokowanyEnums;
    }
}
