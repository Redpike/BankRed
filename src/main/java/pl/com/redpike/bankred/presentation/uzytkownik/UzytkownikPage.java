package pl.com.redpike.bankred.presentation.uzytkownik;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.enums.UzytkownikZablokowanyEnum;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.util.CRUDButtonLayout;

/**
 * Created by Redpike
 */
public class UzytkownikPage extends Panel {

    private final UzytkownikView uzytkownikView;

    private MVerticalLayout verticalLayout;
    private CRUDButtonLayout crudButtonLayout;
    private MTable<Uzytkownik> table;
    private UzytkownikAddEditWindow uzytkownikAddEditWindow;

    public UzytkownikPage(UzytkownikView uzytkownikView) {
        this.uzytkownikView = uzytkownikView;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        crudButtonLayout = new CRUDButtonLayout();
        initUzytkownikTable();
        verticalLayout = new MVerticalLayout().withMargin(true).withSpacing(true).withFullWidth()
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
            uzytkownikAddEditWindow = new UzytkownikAddEditWindow();
            uzytkownikAddEditWindow.openForSelectedUzytkownik(new Uzytkownik());
        });

        crudButtonLayout.getEditButton().addClickListener(clickEvent -> {
            uzytkownikAddEditWindow = new UzytkownikAddEditWindow();
            uzytkownikAddEditWindow.openForSelectedUzytkownik(table.getValue());
        });

        crudButtonLayout.getDeleteButton().addClickListener(clickEvent -> {
            ConfirmDialog.show(getUI(), "Usuwanie użytkownika", "Czy na pewno chcesz usunąć użytkownika " + table.getValue().getNazwa(), "Tak", "Anuluj", confirmDialog -> {
                uzytkownikView.getUzytkownikPresenter().removeUzytkownik(table.getValue());
                table.getContainerDataSource().removeAllItems();
                table.setBeans(uzytkownikView.getUzytkownikPresenter().getAllUzytkowniki());
            });
        });
    }

    private void initUzytkownikTable() {
        table = new MTable<>(Uzytkownik.class);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setSizeFull();
        table.setBeans(uzytkownikView.getUzytkownikPresenter().getAllUzytkowniki());
        table.setVisibleColumns("nazwa", "imie", "nazwisko", "rola", "zablokowany");
        setTableHeaders();
        addColumnConverter();
    }

    private void setTableHeaders() {
        table.setColumnHeader("nazwa", "Nazwa użytkownika");
        table.setColumnHeader("imie", "Imię");
        table.setColumnHeader("nazwisko", "Nazwisko");
        table.setColumnHeader("rola", "Rola");
        table.setColumnHeader("zablokowany", "Czy zablokowany");
    }

    private void addColumnConverter() {
        table.addGeneratedColumn("zablokowany", (Table.ColumnGenerator) (table, itemId, columnId) -> {
            if (columnId != null) {
                Object value = table.getContainerProperty(itemId, columnId).getValue();

                return ((UzytkownikZablokowanyEnum) value).getDescription();
            }
            return null;
        });
    }
}
