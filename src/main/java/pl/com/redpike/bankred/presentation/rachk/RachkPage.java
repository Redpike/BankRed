package pl.com.redpike.bankred.presentation.rachk;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import pl.com.redpike.bankred.control.rachk.RachkPresenter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by Redpike
 */
@CDIView(RachkPage.VIEW_ID)
@ViewMenuItem(title = "Rachunki", order = 6, icon = FontAwesome.MONEY)
public class RachkPage extends CustomComponent implements View {

    public static final String VIEW_ID = "rachunek";

    @Inject
    private RachkPresenter rachkPresenter;

    @PostConstruct
    public void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new RachkView(rachkPresenter, this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public RachkPresenter getRachkPresenter() {
        return rachkPresenter;
    }
}
