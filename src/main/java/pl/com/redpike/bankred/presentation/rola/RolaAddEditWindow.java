package pl.com.redpike.bankred.presentation.rola;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.rola.Rola;

/**
 * Created by rs3 on 14.03.2017.
 */
public class RolaAddEditWindow extends Window {

    private final RolaPage rolaPage;
    private Rola rola;

    private RolaForm rolaForm;
    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private Button zapiszButton;
    private Button anulujButton;
    private Button.ClickListener addListener;
    private Button.ClickListener editListener;

    public RolaAddEditWindow(RolaPage rolaPage) {
        this.rolaPage = rolaPage;

        initWindow();
        initComponents();
        addListeners();

        setContent(mainLayout);
    }

    private void initWindow() {
        setCaption(" Dodaj rolę");
        setIcon(FontAwesome.USER_MD);
        setModal(true);
        setResizable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        setHeight(200, Unit.PIXELS);
        setWidth(410, Unit.PIXELS);
    }

    private void initComponents() {
        rolaForm = new RolaForm(this);

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
                .with(rolaForm, buttonLayout)
                .withSpacing(true)
                .withMargin(true)
                .withFullWidth()
                .withFullHeight()
                .withAlign(buttonLayout, Alignment.BOTTOM_CENTER);
    }

    private void addListeners() {
        createAddListener();
        createEditListener();
        zapiszButton.addClickListener(addListener);
        anulujButton.addClickListener(event -> close());
    }

    private void createAddListener() {
        addListener = event -> {
            if (rolaForm.isValid()) {
                Rola rola = rolaForm.getEntity();
                rolaPage.getRolaPresenter().addRola(rola);
                rolaPage.getRolaPresenter().getView().refreshTable();
                Notification.show("Zapisano rolę " + rola.getNazwa(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    private void createEditListener() {
        editListener = event -> {
            if (rolaForm.isValid()) {
                Rola rola = rolaForm.getEntity();
                rolaPage.getRolaPresenter().editRola(rola);
                rolaPage.getRolaPresenter().getView().refreshTable();
                Notification.show("Zapisano rolę " + rola.getNazwa(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    public void openForSelectedRola(Rola rola) {
        UI.getCurrent().addWindow(this);
        setCaption(" Edytuj rolę");
        this.rola = rola;
        zapiszButton.removeClickListener(addListener);
        zapiszButton.addClickListener(editListener);
        rolaForm.setSelectedRola(this.rola);
    }
}
