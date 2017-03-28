package pl.com.redpike.bankred.presentation.components.windows;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Redpike
 */
public class SettingsButton extends Button {

    public SettingsButton() {
        setIcon(FontAwesome.GEAR);
        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        setDescription("Ustawienia");
    }
}
