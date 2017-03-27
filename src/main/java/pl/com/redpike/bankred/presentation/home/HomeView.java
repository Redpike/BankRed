package pl.com.redpike.bankred.presentation.home;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.control.home.HomePresenter;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;

/**
 * Created by rs3 on 22.02.2017.
 */
public class HomeView extends AbstractView<HomePresenter> {

    private final HomePage homePage;
    private HomePresenter homePresenter;

    private VerticalLayout layout;
    private HorizontalLayout daneUzytkownikaLayout;
    private Panel uzytkownikPanel;
    private Label welcomeLabel;
    private Label imieUzytkownikaLabel;
    private Label nazwiskoUzytkownikaLabel;
    private Label rolaUzytkownikaLabel;

    public HomeView(HomePresenter homePresenter, HomePage homePage) {
        super(homePresenter);
        this.homePage = homePage;

        initComponents();
        initLayout();
    }

    private void initComponents() {
        setCaption(" Pulpit");
        setIcon(FontAwesome.BANK);
        setSizeFull();

        uzytkownikPanel = new Panel(" Panel użytkownika");
        uzytkownikPanel.setIcon(FontAwesome.USER);
        uzytkownikPanel.setSizeFull();

        imieUzytkownikaLabel = new MLabel().withCaption("Imię:");
        nazwiskoUzytkownikaLabel = new MLabel().withCaption("Nazwisko:");
        rolaUzytkownikaLabel = new MLabel().withCaption("Rola:");

        daneUzytkownikaLayout = new MHorizontalLayout().with(imieUzytkownikaLabel, nazwiskoUzytkownikaLabel, rolaUzytkownikaLabel).withMargin(true).withSpacing(true).withFullWidth();

        welcomeLabel = new MLabel("Witaj w aplikacji bankowej BankRed System");

        layout = new MVerticalLayout(welcomeLabel, uzytkownikPanel).withFullWidth().withFullHeight().withSpacing(true).withMargin(true).withAlign(welcomeLabel, Alignment.MIDDLE_CENTER);
    }

    private void initLayout() {
        uzytkownikPanel.setContent(daneUzytkownikaLayout);
        setContent(layout);
    }

    public void refreshUzytkownikData(Uzytkownik uzytkownik) {
        imieUzytkownikaLabel.setValue(uzytkownik.getImie());
        nazwiskoUzytkownikaLabel.setValue(uzytkownik.getNazwisko());
        rolaUzytkownikaLabel.setValue(uzytkownik.getRola().getNazwa());
    }

    @Override
    public HomePresenter getPresenter() {
        return homePresenter;
    }
}
