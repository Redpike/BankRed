package pl.com.redpike.bankred.presentation.login;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import pl.com.redpike.bankred.control.login.LoginPresenter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by rs3 on 22.02.2017.
 */
@CDIView(LoginPage.VIEW_ID)
@ViewMenuItem(enabled = false)
public class LoginPage extends CustomComponent implements View {

    public static final String VIEW_ID = "";

    @Inject
    private LoginPresenter loginPresenter;

    @PostConstruct
    private void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new LoginView(this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public LoginPresenter getPresenter(){
        return loginPresenter;
    }
}
