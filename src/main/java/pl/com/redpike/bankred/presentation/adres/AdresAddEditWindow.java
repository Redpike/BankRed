package pl.com.redpike.bankred.presentation.adres;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;
import pl.com.redpike.bankred.business.adres.Adres;
import pl.com.redpike.bankred.presentation.klient.KlientAddEditWindow;

/**
 * Created by Redpike
 */
public class AdresAddEditWindow extends MWindow {

    private KlientAddEditWindow klientAddEditWindow;

    private AdresForm adresForm;
    private Adres adres;
    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private Button zapiszButton;
    private Button anulujButton;
    private Button.ClickListener addListener;

    public AdresAddEditWindow(KlientAddEditWindow klientAddEditWindow) {
        this.klientAddEditWindow = klientAddEditWindow;

        initWindow();
        initComponents();
        addListeners();

        setContent(mainLayout);
    }

    private void initWindow() {
        setCaption(" Podaj adres");
        setIcon(FontAwesome.HOME);
        setModal(true);
        setResizable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        setHeight(400, Unit.PIXELS);
        setWidth(480, Unit.PIXELS);
    }

    private void initComponents() {
        adresForm = new AdresForm();

        mainLayout = new MVerticalLayout();

        zapiszButton = new MButton("Zapisz")
                .withIcon(FontAwesome.FLOPPY_O).withStyleName(ValoTheme.BUTTON_SMALL);

        anulujButton = new MButton("Anuluj")
                .withIcon(FontAwesome.UNDO).withStyleName(ValoTheme.BUTTON_SMALL);

        buttonLayout = new MHorizontalLayout()
                .with(zapiszButton, anulujButton)
                .withSpacing(true)
                .withFullWidth()
                .withAlign(zapiszButton, Alignment.BOTTOM_LEFT)
                .withAlign(anulujButton, Alignment.BOTTOM_RIGHT);

        mainLayout = new MVerticalLayout()
                .with(adresForm, buttonLayout)
                .withSpacing(true)
                .withMargin(true)
                .withFullWidth()
                .withFullHeight()
                .withAlign(buttonLayout, Alignment.BOTTOM_CENTER);
    }

    private void addListeners() {
        createAddListener();
        zapiszButton.addClickListener(addListener);
        anulujButton.addClickListener(event -> close());
    }

    private void createAddListener() {
        addListener = event -> {
            if (adresForm.isValid()) {
                klientAddEditWindow.setAdres(adresForm.getEntity());
                Notification.show("Zapisano adres", Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    public void openForSelectedKlient(Adres adres) {
        UI.getCurrent().addWindow(this);
        this.adres = adres;
        adresForm.setSelectedAdres(this.adres);
    }
}
