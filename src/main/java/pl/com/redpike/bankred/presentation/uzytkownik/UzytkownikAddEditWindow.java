package pl.com.redpike.bankred.presentation.uzytkownik;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;

/**
 * Created by Redpike
 */
public class UzytkownikAddEditWindow extends Window {

    private final UzytkownikView uzytkownikView;

    private UzytkownikForm uzytkownikForm;
    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private Button zapiszButton;
    private Button anulujButton;

    public UzytkownikAddEditWindow(UzytkownikView uzytkownikView) {
        this.uzytkownikView = uzytkownikView;

        initWindow();
        initComponents();
        initLayout();
        addListeners();

        setContent(mainLayout);
    }

    private void initWindow() {
        setCaption(" Dodaj/Edytuj użytkownika");
        setIcon(FontAwesome.USER);
        setModal(true);
        setResizable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        setHeight(550, Unit.PIXELS);
        setWidth(480, Unit.PIXELS);
    }

    private void initComponents() {
        uzytkownikForm = new UzytkownikForm(this);

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
                .with(uzytkownikForm, buttonLayout)
                .withSpacing(true)
                .withMargin(true)
                .withFullWidth()
                .withFullHeight()
                .withAlign(buttonLayout, Alignment.BOTTOM_CENTER);
    }

    private void initLayout() {

    }

    private void addListeners() {
        zapiszButton.addClickListener(event -> {
            if (uzytkownikForm.isValid()) {
                Uzytkownik uzytkownik = uzytkownikForm.getEntity();
                uzytkownikView.getUzytkownikPresenter().addUzytkownik(uzytkownik);
                Notification.show("Zapisano użytkownika " + uzytkownik.getNazwa(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formuularza", Notification.Type.ERROR_MESSAGE);
        });

        anulujButton.addClickListener(event -> close());
    }

    public void openForSelectedUzytkownik(Uzytkownik uzytkownik) {
        UI.getCurrent().addWindow(this);
        uzytkownikForm.setSelectedUzytkownik(uzytkownik);
    }

    public UzytkownikView getUzytkownikView() {
        return uzytkownikView;
    }
}
