package pl.com.redpike.bankred.presentation.error;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;

import javax.annotation.PostConstruct;

/**
 * Created by Redpike
 */
@CDIView(ErrorPage.VIEW_ID)
@ViewMenuItem(enabled = false)
public class ErrorPage extends CustomComponent implements View {

    public static final String VIEW_ID = "error";

    @PostConstruct
    public void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new ErrorView());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
