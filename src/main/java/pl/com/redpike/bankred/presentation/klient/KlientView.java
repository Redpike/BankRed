package pl.com.redpike.bankred.presentation.klient;

import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.enums.PlecEnum;
import pl.com.redpike.bankred.business.klient.Klient;
import pl.com.redpike.bankred.control.klient.KlientPresenter;
import pl.com.redpike.bankred.presentation.components.CRUDButtonLayout;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;
import pl.com.redpike.bankred.util.properties.BankRedProperites;
import pl.com.redpike.bankred.util.properties.KlientPropertyUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Redpike
 */
public class KlientView extends AbstractView<KlientPresenter> {

    private KlientPage klientPage;

    private MVerticalLayout verticalLayout;
    private CRUDButtonLayout buttonLayout;
    private MTable<Klient> table;
    private KlientAddEditWindow klientAddEditWindow;

    public KlientView(KlientPresenter presenter, KlientPage klientPage) {
        super(presenter);
        this.klientPage = klientPage;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        buttonLayout = new CRUDButtonLayout();
        buttonLayout.getEditButton().setEnabled(false);
        buttonLayout.getDeleteButton().setEnabled(false);

        initKlientTable();
        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .with(buttonLayout, table);
    }

    private void initLayout() {
        setCaption(" Klienci");
        setIcon(FontAwesome.USERS);
        setSizeFull();
        setContent(verticalLayout);
    }

    private void addListeners() {
        buttonLayout.getAddButton().addClickListener(event -> {
            klientAddEditWindow = new KlientAddEditWindow(klientPage);
            getUI().addWindow(klientAddEditWindow);
        });

        buttonLayout.getEditButton().addClickListener(event -> {
           klientAddEditWindow = new KlientAddEditWindow(klientPage);
           klientAddEditWindow.openForSelectedKlient(table.getValue());
        });

        buttonLayout.getDeleteButton().addClickListener(event -> {
            Klient klient = table.getValue();

            ConfirmDialog.show(getUI(), "Usuwanie klienta", "Czy na pewno chcesz usunąć klienta " + klient.getModulo(), "Tak", "Anuluj", confirmDialog -> {
                if (confirmDialog.isConfirmed()) {
                    getPresenter().removeKlient(klient);
                    refreshTable();
                    Notification.show("Klient " + klient.getModulo() + " został usunięty", Notification.Type.TRAY_NOTIFICATION);
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

    private void initKlientTable() {
        table = new MTable<>(Klient.class);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setSizeFull();
        table.setVisibleColumns(
                KlientPropertyUtil.MODULO,
                KlientPropertyUtil.PESEL,
                KlientPropertyUtil.REGON,
                KlientPropertyUtil.IMIE,
                KlientPropertyUtil.NAZWISKO,
                KlientPropertyUtil.DATA_URODZENIA,
                KlientPropertyUtil.PLEC,
                KlientPropertyUtil.ADRES
        );

        setTableHeaders();
        addColumnConverter();
        refreshTable();
    }

    private void setTableHeaders() {
        table.setColumnHeaders(
                KlientPropertyUtil.MODULO_HEADER,
                KlientPropertyUtil.PESEL_HEADER,
                KlientPropertyUtil.REGON_HEADER,
                KlientPropertyUtil.IMIE_HEADER,
                KlientPropertyUtil.NAZWISKO_HEADER,
                KlientPropertyUtil.DATA_URODZENIA_HEADER,
                KlientPropertyUtil.PLEC_HEADER,
                KlientPropertyUtil.ADRES_HEADER
        );
    }

    private void addColumnConverter() {
        table.addGeneratedColumn(KlientPropertyUtil.PLEC, (Table.ColumnGenerator) (table, itemId, columnId) -> {
            if (columnId != null) {
                Object value = table.getContainerProperty(itemId, columnId).getValue();
                return ((PlecEnum) value).getDescription();
            }
            return null;
        });

        table.setConverter(KlientPropertyUtil.DATA_URODZENIA, new StringToDateConverter() {
            @Override
            public DateFormat getFormat(Locale locale) {
                return new SimpleDateFormat(BankRedProperites.DATE_FORMAT);
            }
        });
    }
}
