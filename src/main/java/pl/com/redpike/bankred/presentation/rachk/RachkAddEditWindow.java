package pl.com.redpike.bankred.presentation.rachk;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.rachk.Rachk;

/**
 * Created by Redpike
 */
public class RachkAddEditWindow extends Window {

    private RachkPage rachkPage;
    private Rachk rachk;

    private RachkForm rachkForm;
    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private Button zapiszButton;
    private Button anulujButton;
    private Button.ClickListener addListener;
    private Button.ClickListener editListener;

    public RachkAddEditWindow(RachkPage rachkPage) {
        this.rachkPage = rachkPage;

        initWindow();
        initComponents();
        addListeners();

        setContent(mainLayout);
    }

    private void initWindow() {
        setCaption(" Dodaj rachunek");
        setIcon(FontAwesome.MONEY);
        setModal(true);
        setResizable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        setHeight(350, Unit.PIXELS);
        setWidth(450, Unit.PIXELS);
    }

    private void initComponents() {
        rachkForm = new RachkForm(this);

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
                .with(rachkForm, buttonLayout)
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
            if (rachkForm.isValid()) {
                Rachk rachk = rachkForm.getEntity();
                rachkPage.getRachkPresenter().addRachk(rachk);
                rachkPage.getRachkPresenter().getView().refreshTable();
                Notification.show("Zapisano rachunek " + rachk.getRachunek(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    private void createEditListener() {
        editListener = event -> {
            if (rachkForm.isValid()) {
                Rachk rachk = rachkForm.getEntity();
                rachkPage.getRachkPresenter().editRachk(rachk);
                rachkPage.getRachkPresenter().getView().refreshTable();
                Notification.show("Edytowano rachunek " + rachk.getRachunek(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Niepoprawne dane formularza", Notification.Type.ERROR_MESSAGE);
        };
    }

    public void openForSelectedRachk(Rachk rachk) {
        UI.getCurrent().addWindow(this);
        setCaption(" Edytuj rachunek");
        this.rachk = rachk;
        zapiszButton.removeClickListener(addListener);
        zapiszButton.addClickListener(editListener);
        rachkForm.setSelectedRachk(this.rachk);
    }

    public RachkPage getRachkPage() {
        return rachkPage;
    }
}
