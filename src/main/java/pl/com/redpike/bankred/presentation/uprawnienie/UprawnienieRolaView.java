package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.business.uprawnienie.Uprawnienie;
import pl.com.redpike.bankred.control.uprawnienie.UprawnieniePresenter;
import pl.com.redpike.bankred.presentation.components.CRUDButtonLayout;
import pl.com.redpike.bankred.util.properties.UprawnieniePropertyUtil;

import java.util.List;
import java.util.Objects;

/**
 * Created by Redpike
 */
public class UprawnienieRolaView extends VerticalLayout {

    private static final String UPRAWNIENIA_PANEL_CAPTION = " Uprawnienia nadane roli ";

    private UprawnieniePresenter uprawnieniePresenter;
    private UprawnieniePage uprawnieniePage;

    private MVerticalLayout verticalLayout;
    private ComboBox rolaComboBox;
    private Panel uprawnieniaPanel;
    private MVerticalLayout uprawnieniaLayout;
    private CRUDButtonLayout crudButtonLayout;
    private MTable<Uprawnienie> uprawnienieTable;

    public UprawnienieRolaView(UprawnieniePresenter uprawnieniePresenter, UprawnieniePage uprawnieniePage) {
        this.uprawnieniePresenter = uprawnieniePresenter;
        this.uprawnieniePage = uprawnieniePage;

        initComponents();
        initLayout();
        addListeners();
        setSizeFull();
    }

    private void initComponents() {
        initRolaComboBox();
        initUprawnieniaTable();

        crudButtonLayout = new CRUDButtonLayout();
        crudButtonLayout.getAddButton().setEnabled(false);
        crudButtonLayout.getEditButton().setEnabled(false);
        crudButtonLayout.getDeleteButton().setEnabled(false);

        uprawnieniaLayout = new MVerticalLayout()
                .withFullHeight()
                .withFullWidth()
                .withSpacing(true)
                .withMargin(true)
                .with(crudButtonLayout, uprawnienieTable);

        uprawnieniaPanel = new Panel(UPRAWNIENIA_PANEL_CAPTION);
        uprawnieniaPanel.setSizeFull();
        uprawnieniaPanel.setIcon(FontAwesome.KEY);
        uprawnieniaPanel.setContent(uprawnieniaLayout);

        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .withFullHeight()
                .with(rolaComboBox, uprawnieniaPanel);
    }

    private void initLayout() {
        setCaption(" Nadanie uprawnień");
        setIcon(FontAwesome.RETWEET);
        setSizeFull();
        addComponent(verticalLayout);
    }

    private void addListeners() {
        rolaComboBox.addValueChangeListener(event -> {
            if (Objects.nonNull(rolaComboBox.getValue())) {
                refreshTable((Rola) rolaComboBox.getValue());
                uprawnieniaPanel.setCaption(UPRAWNIENIA_PANEL_CAPTION + ((Rola) rolaComboBox.getValue()).getNazwa());
            } else {
                uprawnienieTable.getContainerDataSource().removeAllItems();
                uprawnieniaPanel.setCaption(UPRAWNIENIA_PANEL_CAPTION);
            }

            crudButtonLayout.getAddButton().setEnabled(Objects.nonNull(rolaComboBox.getValue()));
            uprawnienieTable.setVisible(Objects.nonNull(rolaComboBox.getValue()));
        });

        initCRUDListeners();
    }

    private void initRolaComboBox() {
        rolaComboBox = new ComboBox();
        rolaComboBox.setCaption("Wybierz rolę");
        rolaComboBox.setNullSelectionAllowed(true);
        rolaComboBox.setImmediate(true);
        rolaComboBox.setWidth(300, Unit.PIXELS);
        List<Rola> rolaList = uprawnieniePage.getUprawnieniePresenter().findAllRola();
        rolaComboBox.addItems(rolaList);
        rolaList.forEach(rola -> rolaComboBox.setItemCaption(rola, rola.getNazwa()));
    }

    private void initUprawnieniaTable() {
        uprawnienieTable = new MTable<>(Uprawnienie.class);
        uprawnienieTable.setVisible(false);
        uprawnienieTable.setSelectable(true);
        uprawnienieTable.setImmediate(true);
        uprawnienieTable.setSizeFull();
        uprawnienieTable.setVisibleColumns(UprawnieniePropertyUtil.NAZWA);
        uprawnienieTable.setColumnHeader(UprawnieniePropertyUtil.NAZWA, UprawnieniePropertyUtil.NAZWA_HEADER);
    }

    private void initCRUDListeners() {
        crudButtonLayout.getAddButton().addClickListener(event -> {
            UI.getCurrent().addWindow(new UprawnienieRolaAddWindow(uprawnieniePage, (Rola) rolaComboBox.getValue()));
        });

        crudButtonLayout.getDeleteButton().addClickListener(event -> {
            Uprawnienie uprawnienie = uprawnienieTable.getValue();

            ConfirmDialog.show(getUI(), "Usuwanie uprawnienia", "Czy na pewno chcesz usunąć uprawnienie " + uprawnienie.getNazwa(), "Tak", "Anuluj", confirmDialog -> {
                if (confirmDialog.isConfirmed()) {
                    uprawnieniePage.getUprawnieniePresenter().removeUprawnienieForRola(uprawnienie, (Rola) rolaComboBox.getValue());
                    refreshTable((Rola) rolaComboBox.getValue());
                    Notification.show("Uprawnienie " + uprawnienie.getNazwa() + " zostało usunięte", Notification.Type.TRAY_NOTIFICATION);
                }
            });
        });

        uprawnienieTable.addValueChangeListener(event -> {
            if (event.getProperty().getValue() != null)
                crudButtonLayout.getDeleteButton().setEnabled(true);
            else
                crudButtonLayout.getDeleteButton().setEnabled(false);
        });
    }

    public void refreshTable(Rola rola) {
        uprawnienieTable.getContainerDataSource().removeAllItems();
        uprawnienieTable.setBeans(uprawnieniePage.getUprawnieniePresenter().getAllUprawnieniaForRola(rola));
    }
}
