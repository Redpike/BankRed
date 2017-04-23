package pl.com.redpike.bankred.presentation.rachk;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.rachk.Rachk;
import pl.com.redpike.bankred.control.converters.DateConverter;
import pl.com.redpike.bankred.control.rachk.RachkPresenter;
import pl.com.redpike.bankred.presentation.components.CRUDButtonLayout;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;
import pl.com.redpike.bankred.util.properties.RachkPropertyUtil;

/**
 * Created by Redpike
 */
public class RachkView extends AbstractView<RachkPresenter> {

    private final RachkPage rachkPage;

    private MVerticalLayout verticalLayout;
    private CRUDButtonLayout buttonLayout;
    private MTable<Rachk> table;
    private RachkAddEditWindow rachkAddEditWindow;
    private DateConverter dateConverter;

    public RachkView(RachkPresenter rachkPresenter, RachkPage rachkPage) {
        super(rachkPresenter);
        this.rachkPage = rachkPage;

        initComponents();
        intiLayout();
        addListeners();
    }

    private void initComponents() {
        buttonLayout = new CRUDButtonLayout();
        buttonLayout.getEditButton().setEnabled(false);
        buttonLayout.getDeleteButton().setEnabled(false);
        dateConverter = new DateConverter();

        initRachunkiTable();
        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .with(buttonLayout, table);
    }

    private void intiLayout() {
        setCaption(" Rachunki");
        setIcon(FontAwesome.MONEY);
        setSizeFull();
        setContent(verticalLayout);
    }

    private void addListeners() {
        buttonLayout.getAddButton().addClickListener(event -> {
            rachkAddEditWindow = new RachkAddEditWindow(rachkPage);
            getUI().addWindow(rachkAddEditWindow);
        });

        buttonLayout.getEditButton().addClickListener(event -> {
            rachkAddEditWindow = new RachkAddEditWindow(rachkPage);
            rachkAddEditWindow.openForSelectedRachk(table.getValue());
        });

        buttonLayout.getDeleteButton().addClickListener(event -> {
            Rachk rachk = table.getValue();

            ConfirmDialog.show(getUI(), "Usuwanie rachunku", "Czy na pewno chcesz usunąć rachunek " + rachk.getRachunek(), "Tak", "Anuluj", confirmDialog -> {
                if (confirmDialog.isConfirmed()) {
                    getPresenter().removeRachk(rachk);
                    refreshTable();
                    Notification.show("Rachunek " + rachk.getRachunek() + " został usunięty", Notification.Type.TRAY_NOTIFICATION);
                }
            });
        });

        table.addValueChangeListener(event -> {
            if (event.getProperty().getValue() != null) {
                buttonLayout.getEditButton().setEnabled(true);
                buttonLayout.getDeleteButton().setEnabled(true);
            } else {
                buttonLayout.getEditButton().setEnabled(false);
                buttonLayout.getDeleteButton().setEnabled(false);
            }
        });
    }

    public void refreshTable() {
        table.getContainerDataSource().removeAllItems();
        table.setBeans(getPresenter().findAll());
    }

    private void initRachunkiTable() {
        table = new MTable<>(Rachk.class);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setSizeFull();
        table.setVisibleColumns(
                RachkPropertyUtil.RACHUNEK,
                RachkPropertyUtil.KLIENT,
                RachkPropertyUtil.DATA_UTWORZENIA,
                RachkPropertyUtil.DATA_ZAMKNIECIA,
                RachkPropertyUtil.STAN_KONTA
        );

        setTableHeaders();
        addColumnConverter();
        refreshTable();
    }

    private void setTableHeaders() {
        table.setColumnHeaders(
                RachkPropertyUtil.RACHUNEK_HEADER,
                RachkPropertyUtil.KLIENT_HEADER,
                RachkPropertyUtil.DATA_UTWORZENIA_HEADER,
                RachkPropertyUtil.DATA_ZAMKNIECIA_HEADER,
                RachkPropertyUtil.STAN_KONTA_HEADER
        );
    }

    private void addColumnConverter() {
        table.setConverter(RachkPropertyUtil.DATA_UTWORZENIA, dateConverter);
        table.setConverter(RachkPropertyUtil.DATA_ZAMKNIECIA, dateConverter);
    }
}
