package pl.com.redpike.bankred.presentation.home;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * Created by rs3 on 22.02.2017.
 */
public class HomePage extends VerticalLayout {

    private final HomeView homeView;

    private VerticalLayout layout;
    private Label label;

    public HomePage(HomeView homeView) {
        this.homeView = homeView;

        initComponents();
        initLayout();
    }

    private void initComponents() {
        label = new MLabel("Witaj w aplikacji bankowej BankRed System");

        layout = new MVerticalLayout(label).withFullWidth().withFullHeight().withSpacing(true).withMargin(true).withAlign(label, Alignment.MIDDLE_CENTER);
    }

    private void initLayout() {
        addComponent(layout);
    }

}
