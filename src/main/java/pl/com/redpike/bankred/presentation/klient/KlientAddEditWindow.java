package pl.com.redpike.bankred.presentation.klient;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.adres.Adres;
import pl.com.redpike.bankred.business.klient.Klient;
import pl.com.redpike.bankred.presentation.adres.AdresAddEditWindow;

import java.util.Objects;

/**
 * Created by Redpike
 */
public class KlientAddEditWindow extends Window {

    private KlientPage klientPage;
    private Klient klient;
    private Adres adres;

    private KlientForm klientForm;
    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private Button adresButton;
    private Button zapiszButton;
    private Button anulujButton;
    private Button.ClickListener addListener;
    private Button.ClickListener editListener;

    public KlientAddEditWindow(KlientPage klientPage) {
        this.klientPage = klientPage;

        initWindow();
        initComponents();
        addListeners();

        setContent(mainLayout);
    }

    private void initWindow() {
        setCaption(" Dodaj klienta");
        setIcon(FontAwesome.USER);
        setModal(true);
        setResizable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        setHeight(650, Unit.PIXELS);
        setWidth(450, Unit.PIXELS);
    }

    private void initComponents() {
        klientForm = new KlientForm(this);

        adresButton = new MButton("Adres").withIcon(FontAwesome.HOME)
                .withStyleName(ValoTheme.BUTTON_SMALL);
        adresButton.setWidth(100, Unit.PIXELS);

        zapiszButton = new MButton("Zapisz")
                .withIcon(FontAwesome.FLOPPY_O).withStyleName(ValoTheme.BUTTON_SMALL);

        anulujButton = new MButton("Anuluj")
                .withIcon(FontAwesome.UNDO).withStyleName(ValoTheme.BUTTON_SMALL);

        buttonLayout = new MHorizontalLayout()
                .with(zapiszButton, adresButton, anulujButton)
                .withSpacing(true)
                .withFullWidth()
                .withAlign(zapiszButton, Alignment.BOTTOM_LEFT)
                .withAlign(adresButton, Alignment.BOTTOM_CENTER)
                .withAlign(anulujButton, Alignment.BOTTOM_RIGHT);

        mainLayout = new MVerticalLayout()
                .with(klientForm, buttonLayout)
                .withSpacing(true)
                .withMargin(true)
                .withFullWidth()
                .withFullHeight()
                .withAlign(buttonLayout, Alignment.BOTTOM_CENTER);
    }

    private void addListeners() {
        createAddListener();
        createEditListener();
        adresButton.addClickListener(event -> {
            AdresAddEditWindow adresAddEditWindow = new AdresAddEditWindow(this);
            if (Objects.isNull(adres))
                UI.getCurrent().addWindow(adresAddEditWindow);
            else
                adresAddEditWindow.openForSelectedKlient(adres);
        });
        zapiszButton.addClickListener(addListener);
        anulujButton.addClickListener(event -> close());
    }

    private void createAddListener() {
        addListener = event -> {
            if (klientForm.isValid()) {
                Klient klient = klientForm.getEntity();
                klient.setAdres(adres);
                klientPage.getKlientPresenter().addKlient(klient);
                klientPage.getKlientPresenter().getView().refreshTable();
                Notification.show("Zapisano klienta " + klient.getModulo(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    private void createEditListener() {
        editListener = event -> {
            if (klientForm.isValid()) {
                Klient klient = klientForm.getEntity();
                klient.setAdres(adres);
                klientPage.getKlientPresenter().editKlient(klient);
                klientPage.getKlientPresenter().getView().refreshTable();
                Notification.show("Zapisano użytkownika", Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    public void openForSelectedKlient(Klient klient) {
        UI.getCurrent().addWindow(this);
        setCaption(" Edytuj klienta");
        this.klient = klient;
        this.adres = klient.getAdres();
        zapiszButton.removeClickListener(addListener);
        zapiszButton.addClickListener(editListener);
        klientForm.setSelectedKlient(this.klient);
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public KlientPage getKlientPage() {
        return klientPage;
    }
}
