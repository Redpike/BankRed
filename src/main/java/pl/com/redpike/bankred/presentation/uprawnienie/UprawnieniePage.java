package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.uprawnienie.Uprawnienie;
import pl.com.redpike.bankred.control.uprawnienie.UprawnieniePresenter;
import pl.com.redpike.bankred.presentation.components.CRUDButtonLayout;
import pl.com.redpike.bankred.util.properties.UprawnieniePropertyUtil;

/**
 * Created by Redpike
 */
public class UprawnieniePage extends VerticalLayout {

    private UprawnieniePresenter uprawnieniePresenter;
    private UprawnienieView uprawnienieView;

    private MVerticalLayout verticalLayout;
    private CRUDButtonLayout crudButtonLayout;
    private MTable<Uprawnienie> table;
    private UprawnienieAddEditWindow uprawnienieAddEditWindow;

    public UprawnieniePage(UprawnieniePresenter uprawnieniePresenter, UprawnienieView uprawnienieView) {
        this.uprawnieniePresenter = uprawnieniePresenter;
        this.uprawnienieView = uprawnienieView;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        crudButtonLayout = new CRUDButtonLayout();
        crudButtonLayout.getEditButton().setEnabled(false);
        crudButtonLayout.getDeleteButton().setEnabled(false);

        initUprawnieniaTable();
        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .with(crudButtonLayout, table);
    }

    private void initLayout() {
        setCaption(" Uprawnienia");
        setIcon(FontAwesome.KEY);
        setSizeFull();
        addComponent(verticalLayout);
    }

    private void addListeners() {
        crudButtonLayout.getAddButton().addClickListener(clickEvent -> {
            uprawnienieAddEditWindow = new UprawnienieAddEditWindow(uprawnienieView);
            getUI().addWindow(uprawnienieAddEditWindow);
        });

        crudButtonLayout.getEditButton().addClickListener(clickEvent -> {
            uprawnienieAddEditWindow = new UprawnienieAddEditWindow(uprawnienieView);
            uprawnienieAddEditWindow.openForSelectedUzytkownik(table.getValue());
        });

        crudButtonLayout.getDeleteButton().addClickListener(clickEvent -> {
            Uprawnienie uprawnienie = table.getValue();

            ConfirmDialog.show(getUI(), "Usuwanie uprawnienia", "Czy na pewno chcesz usunąć uprawnienie " + uprawnienie.getNazwa(), "Tak", "Anuluj", confirmDialog -> {
                if (confirmDialog.isConfirmed()) {
                    uprawnienieView.getUprawnieniePresenter().removeUprawnienie(uprawnienie);
                    refreshTable();
                    Notification.show("Uprawnienie " + uprawnienie.getNazwa() + " zostało usunięte", Notification.Type.TRAY_NOTIFICATION);
                }
            });
        });

        table.addValueChangeListener(event -> {
            if (event.getProperty().getValue() != null) {
                crudButtonLayout.getEditButton().setEnabled(true);
                crudButtonLayout.getDeleteButton().setEnabled(true);
            } else {
                crudButtonLayout.getEditButton().setEnabled(false);
                crudButtonLayout.getDeleteButton().setEnabled(false);
            }
        });
    }

    private void initUprawnieniaTable() {
        table = new MTable<>(Uprawnienie.class);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setSizeFull();
        table.setVisibleColumns(UprawnieniePropertyUtil.NAZWA);

        setTableHeaders();
        refreshTable();
    }

    public void refreshTable() {
        table.getContainerDataSource().removeAllItems();
        table.setBeans(uprawnienieView.getUprawnieniePresenter().getAllUprawnienia());
    }

    private void setTableHeaders() {
        table.setColumnHeader(UprawnieniePropertyUtil.NAZWA, UprawnieniePropertyUtil.NAZWA_HEADER);
    }

    public UprawnieniePresenter getUprawnieniePresenter() {
        return uprawnieniePresenter;
    }
}
