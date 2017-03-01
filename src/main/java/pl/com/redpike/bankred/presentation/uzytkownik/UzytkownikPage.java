package pl.com.redpike.bankred.presentation.uzytkownik;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Panel;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
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

        table = new MTable<>(Uzytkownik.class);
        table.setSelectable(true);
        table.setBeans(uzytkownikView.getUzytkownikPresenter().getAllUzytkowniki());
        table.setVisibleColumns("nazwa", "imie", "nazwisko");
        table.setColumnHeader("nazwa", "Nazwa użytkownika");
        table.setColumnHeader("imie", "Imię");
        table.setColumnHeader("nazwisko", "Nazwisko");
        table.setWidth(100, Unit.PERCENTAGE);
        table.setImmediate(true);

        verticalLayout = new MVerticalLayout().withMargin(true).withSpacing(true).withFullWidth()
                .with(crudButtonLayout, table);

        uzytkownikAddEditWindow = new UzytkownikAddEditWindow();
    }

    private void initLayout() {
        setCaption(" Użytkownicy");
        setIcon(FontAwesome.USERS);
        setSizeFull();
        setContent(verticalLayout);
    }

    private void addListeners() {
        crudButtonLayout.getAddButton().addClickListener(clickEvent -> uzytkownikAddEditWindow.openForSelectedUzytkownik(new Uzytkownik()));

        crudButtonLayout.getEditButton().addClickListener(clickEvent -> uzytkownikAddEditWindow.openForSelectedUzytkownik(table.getValue()));

        crudButtonLayout.getDeleteButton().addClickListener(clickEvent -> {
            uzytkownikView.getUzytkownikPresenter().removeUzytkownik(table.getValue());
            table.refreshRowCache();
        });
    }
}
