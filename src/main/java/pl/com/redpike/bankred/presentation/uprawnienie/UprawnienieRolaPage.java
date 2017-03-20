package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.control.uprawnienie.UprawnieniePresenter;

/**
 * Created by Redpike
 */
public class UprawnienieRolaPage extends VerticalLayout {

    private UprawnieniePresenter uprawnieniePresenter;
    private UprawnienieView uprawnienieView;

    private MVerticalLayout verticalLayout;
    private Label label;

    public UprawnienieRolaPage(UprawnieniePresenter uprawnieniePresenter, UprawnienieView uprawnienieView) {
        this.uprawnieniePresenter = uprawnieniePresenter;
        this.uprawnienieView = uprawnienieView;

        initComponents();
        initLayout();
    }

    private void initComponents() {
        label = new Label("Drugi tab");

        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .with(label);
    }

    private void initLayout() {
        setCaption(" Nadanie uprawnień");
        setIcon(FontAwesome.RETWEET);
        setSizeFull();
        addComponent(verticalLayout);
    }
}
