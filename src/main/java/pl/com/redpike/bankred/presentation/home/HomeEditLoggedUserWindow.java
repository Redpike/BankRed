package pl.com.redpike.bankred.presentation.home;

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
public class HomeEditLoggedUserWindow extends Window {

    private HomePage homePage;
    private Uzytkownik uzytkownik;

    private PasswordForm passwordForm;
    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private Button zapiszButton;
    private Button anulujButton;
    private Button.ClickListener editListener;

    public HomeEditLoggedUserWindow(HomePage homePage, Uzytkownik uzytkownik) {
        this.homePage = homePage;
        this.uzytkownik = uzytkownik;

        initWindow();
        initComponents();
        addListeners();

        setContent(mainLayout);
    }

    private void initWindow() {
        setCaption(" Zmień hasło");
        setIcon(FontAwesome.USER);
        setModal(true);
        setResizable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        setHeight(300, Unit.PIXELS);
        setWidth(480, Unit.PIXELS);
    }

    private void initComponents() {
        passwordForm = new PasswordForm(this, uzytkownik);

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
                .with(passwordForm, buttonLayout)
                .withSpacing(true)
                .withMargin(true)
                .withFullWidth()
                .withFullHeight()
                .withAlign(buttonLayout, Alignment.BOTTOM_CENTER);
    }

    private void addListeners() {
        createEditListener();
        zapiszButton.addClickListener(editListener);
        anulujButton.addClickListener(event -> close());
    }

    private void createEditListener() {
        editListener = event -> {
            if (passwordForm.isValid()) {
                Uzytkownik uzytkownik = passwordForm.getEntity();
                homePage.getHomePresenter().getUzytkownikWindowPresenter().getUzytkownikPresenter().editUzytkownik(uzytkownik);
                Notification.show("Zmieniono hasło " + uzytkownik.getNazwa(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    public HomePage getHomePage() {
        return homePage;
    }
}
