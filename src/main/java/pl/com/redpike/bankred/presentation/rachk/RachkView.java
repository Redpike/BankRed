package pl.com.redpike.bankred.presentation.rachk;

import com.vaadin.server.FontAwesome;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.control.rachk.RachkPresenter;
import pl.com.redpike.bankred.presentation.components.CRUDButtonLayout;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;

/**
 * Created by Redpike
 */
public class RachkView extends AbstractView<RachkPresenter> {

    private final RachkPage rachkPage;

    private MVerticalLayout verticalLayout;
    private CRUDButtonLayout buttonLayout;

    public RachkView(RachkPresenter rachkPresenter, RachkPage rachkPage) {
        super(rachkPresenter);
        this.rachkPage = rachkPage;

        initComponents();
        intiLayout();
        addListeners();
    }

    private void initComponents() {
        buttonLayout = new CRUDButtonLayout();
        buttonLayout.getEditButton().setEnabled(false);
        buttonLayout.getDeleteButton().setEnabled(false);

        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .with(buttonLayout);
    }

    private void intiLayout() {
        setCaption(" Rachunki");
        setIcon(FontAwesome.MONEY);
        setSizeFull();
        setContent(verticalLayout);
    }

    private void addListeners() {

    }
}
