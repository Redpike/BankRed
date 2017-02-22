package pl.com.redpike.bankred.presentation.home;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;

import javax.annotation.PostConstruct;

/**
 * Created by rs3 on 22.02.2017.
 */
@CDIView(HomeView.VIEW_ID)
public class HomeView extends CustomComponent implements View {

    public static final String VIEW_ID = "home";

    private Navigator navigator;

    @PostConstruct
    private void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new HomePage(this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = getUI().getNavigator();
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
