package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import pl.com.redpike.bankred.control.uprawnienie.UprawnieniePresenter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by Redpike
 */
@CDIView(UprawnieniePage.VIEW_ID)
@ViewMenuItem(title = "Uprawnienia", order = 3, icon = FontAwesome.KEY)
public class UprawnieniePage extends CustomComponent implements View {

    public static final String VIEW_ID = "uprawnienie";

    @Inject
    private UprawnieniePresenter uprawnieniePresenter;

    @PostConstruct
    public void init() {
        buildLayout();
    }

    private void buildLayout() {
        setCompositionRoot(new UprawnienieTabView(uprawnieniePresenter, this));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public UprawnieniePresenter getUprawnieniePresenter() {
        return uprawnieniePresenter;
    }
}
