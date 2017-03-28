package pl.com.redpike.bankred.presentation.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import pl.com.redpike.bankred.util.styles.Styles;

/**
 * Created by Redpike
 */
public class NotificationButton extends Button {

    public NotificationButton() {
        setIcon(FontAwesome.BELL);
        addStyleName(Styles.NOTIFICATIONS);
        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
    }

    public void setUnreadCount(int count) {
        setCaption(String.valueOf(count));
        setDescription(prepareDescription(count));
    }

    private String prepareDescription(int count) {
        String description = "Powiadomienia";

        if (count > 0 && count < 5) {
            addStyleName(Styles.UNREAD);
            description += " (" + count + " nieprzeczytane)";
        } else if (count >= 5) {
            addStyleName(Styles.UNREAD);
            description += " (" + count + " nieprzeczytanych)";
        } else
            removeStyleName(Styles.UNREAD);

        return description;
    }
}
