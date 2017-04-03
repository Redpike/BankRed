package pl.com.redpike.bankred.presentation.home;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.business.uzytkownik.Uzytkownik;
import pl.com.redpike.bankred.control.home.HomePresenter;
import pl.com.redpike.bankred.presentation.BankRedUI;
import pl.com.redpike.bankred.presentation.components.LogoutButton;
import pl.com.redpike.bankred.presentation.components.NotificationButton;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;
import pl.com.redpike.bankred.presentation.components.windows.SettingsButton;

/**
 * Created by rs3 on 22.02.2017.
 */
public class HomeView extends AbstractView<HomePresenter> {

    private final HomePage homePage;
    private HomePresenter homePresenter;

    private VerticalLayout layout;
    private HorizontalLayout daneUzytkownikaLayout;
    private HorizontalLayout welcomeLayout;
    private SettingsButton settingsButton;
    private LogoutButton logoutButton;
    private Panel uzytkownikPanel;
    private Label imieUzytkownikaLabel;
    private Label nazwiskoUzytkownikaLabel;
    private Label rolaUzytkownikaLabel;

    public HomeView(HomePresenter homePresenter, HomePage homePage) {
        super(homePresenter);
        this.homePresenter = homePresenter;
        this.homePage = homePage;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        setCaption(" Pulpit systemu bankowego BankRed");
        setIcon(FontAwesome.BANK);
        setSizeFull();

        initSettingsHeader();
        initUserDataPanel();

        layout = new MVerticalLayout()
                .with(welcomeLayout, uzytkownikPanel)
                .withFullWidth()
                .withFullHeight()
                .withSpacing(true)
                .withMargin(true);
    }

    private void initLayout() {
        uzytkownikPanel.setContent(daneUzytkownikaLayout);
        setContent(layout);
    }

    private void addListeners() {
        settingsButton.addClickListener(event -> getUI().addWindow(new HomeEditLoggedUserWindow(homePage, homePage.getUzytkownik())));
        logoutButton.addClickListener(event -> ((BankRedUI) getUI()).logout());
    }

    private void initSettingsHeader() {
        Label welcomeLabel = new MLabel("Witaj w aplikacji bankowej BankRed System");
        NotificationButton notificationButton = new NotificationButton();
        notificationButton.setUnreadCount(2);
        settingsButton = new SettingsButton();
        logoutButton = new LogoutButton();

        HorizontalLayout buttonsLayout = new MHorizontalLayout()
                .with(notificationButton, settingsButton, logoutButton)
                .withSpacing(true);

        welcomeLayout = new MHorizontalLayout()
                .with(welcomeLabel, buttonsLayout)
                .withFullWidth()
                .withSpacing(true)
                .withAlign(welcomeLabel, Alignment.MIDDLE_LEFT)
                .withAlign(buttonsLayout, Alignment.MIDDLE_RIGHT);
    }

    private void initUserDataPanel() {
        uzytkownikPanel = new Panel(" Dane użytkownika");
        uzytkownikPanel.setIcon(FontAwesome.USER);
        uzytkownikPanel.setSizeFull();

        imieUzytkownikaLabel = new MLabel().withCaption("Imię:");
        nazwiskoUzytkownikaLabel = new MLabel().withCaption("Nazwisko:");
        rolaUzytkownikaLabel = new MLabel().withCaption("Rola:");

        daneUzytkownikaLayout = new MHorizontalLayout()
                .with(imieUzytkownikaLabel, nazwiskoUzytkownikaLabel, rolaUzytkownikaLabel)
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth();
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
