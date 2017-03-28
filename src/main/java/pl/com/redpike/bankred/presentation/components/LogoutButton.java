package pl.com.redpike.bankred.presentation.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Redpike
 */
public class LogoutButton extends Button {

    public LogoutButton() {
        setIcon(FontAwesome.SIGN_OUT);
        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        setDescription("Wyloguj");
    }
}
