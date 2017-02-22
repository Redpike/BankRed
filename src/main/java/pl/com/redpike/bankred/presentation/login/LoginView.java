package pl.com.redpike.bankred.presentation.login;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;

import javax.annotation.PostConstruct;

/**
 * Created by rs3 on 22.02.2017.
 */
@CDIView(LoginView.VIEW_ID)
public class LoginView extends CustomComponent implements View {

    public static final String VIEW_ID = "login";

    private Navigator navigator;

    @PostConstruct
    private void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new LoginPage(this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = getUI().getNavigator();
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
