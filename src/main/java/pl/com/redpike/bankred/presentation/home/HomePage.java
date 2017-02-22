package pl.com.redpike.bankred.presentation.home;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by rs3 on 22.02.2017.
 */
public class HomePage extends VerticalLayout {

    private final HomeView homeView;

    private Label label;

    public HomePage(HomeView homeView) {
        this.homeView = homeView;

        initComponents();
        initLayout();
        addListeners();
    }

    private void initComponents() {
        label = new Label("Home Page!!!");
    }

    private void initLayout() {
        addComponent(label);
    }

    private void addListeners() {

    }
}
