package pl.com.redpike.bankred.presentation.error;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by Redpike
 */
public class ErrorView extends Panel {

    private static final String ERROR_CAPTION = "Błąd programu";
    private static final String ERROR_TEXT = "Wystąpił błąd w aplikacji, proszę skontaktować się z administratorem";

    private VerticalLayout mainLayout;

    public ErrorView() {
        initComponents();
        initLayout();
    }

    private void initComponents() {
        mainLayout = new MVerticalLayout()
                .with(new MLabel(ERROR_TEXT))
                .withFullWidth()
                .withMargin(true);

        setCaption(ERROR_CAPTION);
    }

    private void initLayout() {
        setContent(mainLayout);
        Notification.show(ERROR_TEXT, Notification.Type.ERROR_MESSAGE);
    }
}
