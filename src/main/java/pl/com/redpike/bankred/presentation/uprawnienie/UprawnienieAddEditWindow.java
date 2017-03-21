package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.uprawnienie.Uprawnienie;

/**
 * Created by Redpike
 */
public class UprawnienieAddEditWindow extends Window {

    private final UprawnieniePage uprawnieniePage;
    private Uprawnienie uprawnienie;

    private UprawnienieForm uprawnienieForm;
    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private Button zapiszButton;
    private Button anulujButton;
    private Button.ClickListener addListener;
    private Button.ClickListener editListener;

    public UprawnienieAddEditWindow(UprawnieniePage uprawnieniePage) {
        this.uprawnieniePage = uprawnieniePage;

        initWindow();
        initComponents();
        addListeners();

        setContent(mainLayout);
    }

    private void initWindow() {
        setCaption(" Dodaj uprawnienie");
        setIcon(FontAwesome.KEY);
        setModal(true);
        setResizable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        setHeight(200, Unit.PIXELS);
        setWidth(450, Unit.PIXELS);
    }

    private void initComponents() {
        uprawnienieForm = new UprawnienieForm(this);

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
                .with(uprawnienieForm, buttonLayout)
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
            if (uprawnienieForm.isValid()) {
                Uprawnienie uprawnienie = uprawnienieForm.getEntity();
                uprawnieniePage.getUprawnieniePresenter().addUprawnienie(uprawnienie);
                uprawnieniePage.getUprawnieniePresenter().getView().getUprawnienieView().refreshTable();
                Notification.show("Zapisano uprawnienie " + uprawnienie.getNazwa(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    private void createEditListener() {
        editListener = event -> {
            if (uprawnienieForm.isValid()) {
                Uprawnienie uprawnienie = uprawnienieForm.getEntity();
                uprawnieniePage.getUprawnieniePresenter().editUprawnienie(uprawnienie);
                uprawnieniePage.getUprawnieniePresenter().getView().getUprawnienieView().refreshTable();
                Notification.show("Zapisano uprawnienie " + uprawnienie.getNazwa(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    public void openForSelectedUzytkownik(Uprawnienie uprawnienie) {
        UI.getCurrent().addWindow(this);
        setCaption(" Edytuj uprawnienie");
        this.uprawnienie = uprawnienie;
        zapiszButton.removeClickListener(addListener);
        zapiszButton.addClickListener(editListener);
        uprawnienieForm.setSelectedUprawnienie(this.uprawnienie);
    }

    public UprawnieniePage getUprawnieniePage() {
        return uprawnieniePage;
    }
}
