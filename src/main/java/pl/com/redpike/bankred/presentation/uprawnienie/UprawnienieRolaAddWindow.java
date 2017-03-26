package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.business.uprawnienie.Uprawnienie;

import java.util.List;

/**
 * Created by Redpike
 */
public class UprawnienieRolaAddWindow extends Window {

    private final Rola rola;
    private UprawnieniePage uprawnieniePage;

    private VerticalLayout mainLayout;
    private HorizontalLayout buttonLayout;
    private ComboBox uprawnienieComboBox;
    private Button zapiszButton;
    private Button anulujButton;
    private Button.ClickListener addListener;

    public UprawnienieRolaAddWindow(UprawnieniePage uprawnieniePage, Rola rola) {
        this.uprawnieniePage = uprawnieniePage;
        this.rola = rola;

        initWindow();
        initComponents();
        addListeners();

        setContent(mainLayout);
    }

    private void initWindow() {
        setCaption(" Nadaj uprawnienie roli");
        setIcon(FontAwesome.KEY);
        setModal(true);
        setResizable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        setHeight(200, Unit.PIXELS);
        setWidth(350, Unit.PIXELS);
    }

    private void initComponents() {
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

        initUprawnienieComboBox();

        mainLayout = new MVerticalLayout()
                .with(uprawnienieComboBox, buttonLayout)
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

    private void initUprawnienieComboBox() {
        uprawnienieComboBox = new ComboBox();
        uprawnienieComboBox.setCaption("Wybierz uprawnienie");
        uprawnienieComboBox.setWidth(325, Unit.PIXELS);
        uprawnienieComboBox.setImmediate(true);
        uprawnienieComboBox.setNullSelectionAllowed(false);
        List<Uprawnienie> uprawnienieList = uprawnieniePage.getUprawnieniePresenter().getAllUprawnienia();
        uprawnienieComboBox.addItems(uprawnienieList);
        uprawnienieComboBox.setValue(uprawnienieList.iterator().next());
        uprawnienieList.forEach(uprawnienie -> uprawnienieComboBox.setItemCaption(uprawnienie, uprawnienie.getNazwa()));
    }

    private void createAddListener() {
        addListener = event -> {
            if (!uprawnieniePage.getUprawnieniePresenter().getAllUprawnieniaForRola(rola).contains(uprawnienieComboBox.getValue())) {
                uprawnieniePage.getUprawnieniePresenter().addUprawnienieForRola((Uprawnienie) uprawnienieComboBox.getValue(), rola);
                uprawnieniePage.getUprawnieniePresenter().getView().getUprawnienieRolaView().refreshTable(rola);
                Notification.show("Nadano uprawnienie " + ((Uprawnienie) uprawnienieComboBox.getValue()).getNazwa() + " dla roli " + rola.getNazwa(), Notification.Type.TRAY_NOTIFICATION);
                close();
            } else
                Notification.show("Rola " + rola.getNazwa() + " posiada już uprawnienie " + ((Uprawnienie) uprawnienieComboBox.getValue()).getNazwa(), Notification.Type.WARNING_MESSAGE);
        };
    }
}
