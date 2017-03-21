package pl.com.redpike.bankred.presentation.rola;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import pl.com.redpike.bankred.control.rola.RolaPresenter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by rs3 on 14.03.2017.
 */
@CDIView(RolaPage.VIEW_ID)
@ViewMenuItem(title = "Role", order = 2, icon = FontAwesome.USER_MD)
public class RolaPage extends CustomComponent implements View {

    public static final String VIEW_ID = "rola";

    @Inject
    private RolaPresenter rolaPresenter;

    @PostConstruct
    public void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new RolaView(rolaPresenter, this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public RolaPresenter getRolaPresenter() {
        return rolaPresenter;
    }
}
