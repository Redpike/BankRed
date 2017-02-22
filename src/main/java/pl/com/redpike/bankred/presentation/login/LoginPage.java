package pl.com.redpike.bankred.presentation.login;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import pl.com.redpike.bankred.presentation.home.HomeView;

/**
 * Created by rs3 on 22.02.2017.
 */
public class LoginPage extends VerticalLayout {

    private final LoginView loginView;

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginPage(LoginView loginView) {
        this.loginView = loginView;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        loginButton = new Button("Login", FontAwesome.SEND_O);

        setSizeFull();
        setMargin(true);
        setSpacing(true);
    }

    private void initLayout() {
        addComponent(usernameField);
        addComponent(passwordField);
        addComponent(loginButton);
    }

    private void addListeners() {
        loginButton.addClickListener(clickEvent -> {
            if (loginView.getNavigator() != null)
                loginView.getNavigator().navigateTo(HomeView.VIEW_ID);
        });
    }
}
