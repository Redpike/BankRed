package pl.com.redpike.bankred.presentation;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import pl.com.redpike.bankred.presentation.login.LoginView;

/**
 * Created by rs3 on 22.02.2017.
 */
@CDIView(BankRedView.VIEW_ID)
public class BankRedView extends CustomComponent implements View{

    public static final String VIEW_ID = "";

    private Navigator navigator;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = getUI().getNavigator();

        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setHeight(100, Unit.PIXELS);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Button button = new Button("Enter to BankRed App", FontAwesome.BANK);
        button.addClickListener(e -> navigator.navigateTo(LoginView.VIEW_ID));

        layout.addComponents(button);
        layout.setMargin(true);
        layout.setSpacing(true);

        setCompositionRoot(layout);
    }
}
