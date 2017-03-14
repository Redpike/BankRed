package pl.com.redpike.bankred.presentation.uzytkownik;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.enums.UzytkownikZablokowanyEnum;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.control.uzytkownik.UzytkownikPresenter;
import pl.com.redpike.bankred.presentation.components.CRUDButtonLayout;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;
import pl.com.redpike.bankred.util.properties.UzytkownikPropertyUtil;

/**
 * Created by Redpike
 */
public class UzytkownikPage extends AbstractView<UzytkownikPresenter> {

    private UzytkownikPresenter uzytkownikPresenter;
    private UzytkownikView uzytkownikView;

    private MVerticalLayout verticalLayout;
    private CRUDButtonLayout crudButtonLayout;
    private MTable<Uzytkownik> table;
    private UzytkownikAddEditWindow uzytkownikAddEditWindow;

    public UzytkownikPage(UzytkownikPresenter uzytkownikPresenter, UzytkownikView uzytkownikView) {
        super(uzytkownikPresenter);
        this.uzytkownikView = uzytkownikView;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        crudButtonLayout = new CRUDButtonLayout();
        crudButtonLayout.getEditButton().setEnabled(false);
        crudButtonLayout.getDeleteButton().setEnabled(false);

        initUzytkownikTable();
        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .with(crudButtonLayout, table);
    }

    private void initLayout() {
        setCaption(" Użytkownicy");
        setIcon(FontAwesome.USERS);
        setSizeFull();
        setContent(verticalLayout);
    }

    private void addListeners() {
        crudButtonLayout.getAddButton().addClickListener(clickEvent -> {
            uzytkownikAddEditWindow = new UzytkownikAddEditWindow(uzytkownikView);
            getUI().addWindow(uzytkownikAddEditWindow);
        });

        crudButtonLayout.getEditButton().addClickListener(clickEvent -> {
            uzytkownikAddEditWindow = new UzytkownikAddEditWindow(uzytkownikView);
            uzytkownikAddEditWindow.openForSelectedUzytkownik(table.getValue());
        });

        crudButtonLayout.getDeleteButton().addClickListener(clickEvent -> {
            Uzytkownik uzytkownik = table.getValue();

            ConfirmDialog.show(getUI(), "Usuwanie użytkownika", "Czy na pewno chcesz usunąć użytkownika " + uzytkownik.getNazwa(), "Tak", "Anuluj", confirmDialog -> {
                if (confirmDialog.isConfirmed()) {
                    uzytkownikView.getUzytkownikPresenter().removeUzytkownik(uzytkownik);
                    refreshTable();
                    Notification.show("Uzytkownik " + uzytkownik.getNazwa() + " został usunięty", Notification.Type.TRAY_NOTIFICATION);
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
        table.setBeans(uzytkownikView.getUzytkownikPresenter().getAllUzytkownicy());
    }

    private void initUzytkownikTable() {
        table = new MTable<>(Uzytkownik.class);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setSizeFull();
        table.setVisibleColumns(
                UzytkownikPropertyUtil.NAZWA,
                UzytkownikPropertyUtil.IMIE,
                UzytkownikPropertyUtil.NAZWISKO,
                UzytkownikPropertyUtil.ROLA,
                UzytkownikPropertyUtil.ZABLOKOWANY
        );

        setTableHeaders();
        addColumnConverter();
        refreshTable();
    }

    private void setTableHeaders() {
        table.setColumnHeader(UzytkownikPropertyUtil.NAZWA, UzytkownikPropertyUtil.NAZWA_HEADER);
        table.setColumnHeader(UzytkownikPropertyUtil.IMIE, UzytkownikPropertyUtil.IMIE_HEADER);
        table.setColumnHeader(UzytkownikPropertyUtil.NAZWISKO, UzytkownikPropertyUtil.NAZWISKO_HEADER);
        table.setColumnHeader(UzytkownikPropertyUtil.ROLA, UzytkownikPropertyUtil.ROLA_HEADER);
        table.setColumnHeader(UzytkownikPropertyUtil.ZABLOKOWANY, UzytkownikPropertyUtil.ZABLOKOWANY_HEADER);
    }

    private void addColumnConverter() {
        table.addGeneratedColumn(UzytkownikPropertyUtil.ZABLOKOWANY, (Table.ColumnGenerator) (table, itemId, columnId) -> {
            if (columnId != null) {
                Object value = table.getContainerProperty(itemId, columnId).getValue();
                return ((UzytkownikZablokowanyEnum) value).getDescription();
            }
            return null;
        });
    }

    @Override
    public UzytkownikPresenter getPresenter() {
        return uzytkownikPresenter;
    }
}
