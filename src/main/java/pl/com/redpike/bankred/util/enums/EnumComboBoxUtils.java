package pl.com.redpike.bankred.util.enums;

import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Redpike
 */
public class EnumComboBoxUtils {

    private static final String CAPTION_PROPERTY_NAME = "caption";

    public static Container createContainerFromEnumClass(Class<? extends Enum<?>> enumClass) {
        LinkedHashMap<Enum<?>, String> enumMap = new LinkedHashMap<>();
        for (Object enumConstant : enumClass.getEnumConstants()) {
            enumMap.put((Enum<?>) enumConstant, enumConstant.toString());
        }

        return createContainerFromMap(enumMap);
    }

    public static Container createContainerFromMap(Map<?, String> hashMap) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(CAPTION_PROPERTY_NAME, String.class, "");

        for (Object itemId : hashMap.keySet()) {
            container.addItem(itemId);
            container.getItem(itemId).getItemProperty(CAPTION_PROPERTY_NAME).setValue(hashMap.get(itemId));
        }
        return container;
    }
}
