package pl.com.redpike.bankred.presentation.rola;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.rola.Rola;
import pl.com.redpike.bankred.control.rola.RolaPresenter;
import pl.com.redpike.bankred.presentation.components.CRUDButtonLayout;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;
import pl.com.redpike.bankred.util.properties.RolaPropertyUtil;
import pl.com.redpike.bankred.util.properties.UzytkownikPropertyUtil;

/**
 * Created by rs3 on 14.03.2017.
 */
public class RolaView extends AbstractView<RolaPresenter> {

    private RolaPresenter rolaPresenter;
    private RolaPage rolaPage;

    private MVerticalLayout verticalLayout;
    private CRUDButtonLayout crudButtonLayout;
    private MTable<Rola> table;
    private RolaAddEditWindow rolaAddEditWindow;

    public RolaView(RolaPresenter rolaPresenter, RolaPage rolaPage) {
        super(rolaPresenter);
        this.rolaPresenter = rolaPresenter;
        this.rolaPage = rolaPage;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        crudButtonLayout = new CRUDButtonLayout();
        crudButtonLayout.getEditButton().setEnabled(false);
        crudButtonLayout.getDeleteButton().setEnabled(false);

        initRolaTable();
        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .with(crudButtonLayout, table);
    }

    private void initLayout() {
        setCaption(" Role");
        setIcon(FontAwesome.USER_MD);
        setSizeFull();
        setContent(verticalLayout);
    }

    private void addListeners() {
        crudButtonLayout.getAddButton().addClickListener(clickEvent -> {
            rolaAddEditWindow = new RolaAddEditWindow(rolaPage);
            getUI().addWindow(rolaAddEditWindow);
        });

        crudButtonLayout.getEditButton().addClickListener(clickEvent -> {
            rolaAddEditWindow = new RolaAddEditWindow(rolaPage);
            rolaAddEditWindow.openForSelectedRola(table.getValue());
        });

        crudButtonLayout.getDeleteButton().addClickListener(clickEvent -> {
            Rola rola = table.getValue();

            ConfirmDialog.show(getUI(), "Usuwanie roli", "Czy na pewno chcesz usunąć rolę " + rola.getNazwa(), "Tak", "Anuluj", confirmDialog -> {
                if (confirmDialog.isConfirmed()) {
                    rolaPage.getRolaPresenter().removeRola(rola);
                    refreshTable();
                    Notification.show("Rola " + rola.getNazwa() + " została usunięta", Notification.Type.TRAY_NOTIFICATION);
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

    public void refreshTable() {
        table.getContainerDataSource().removeAllItems();
        table.setBeans(rolaPage.getRolaPresenter().getAllRole());
    }

    private void initRolaTable() {
        table = new MTable<>(Rola.class);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setSizeFull();
        table.setVisibleColumns(RolaPropertyUtil.NAZWA);

        setTableHeaders();
        refreshTable();
    }

    private void setTableHeaders() {
        table.setColumnHeader(UzytkownikPropertyUtil.NAZWA, UzytkownikPropertyUtil.NAZWA_HEADER);
    }

    @Override
    public RolaPresenter getPresenter() {
        return rolaPresenter;
    }
}
