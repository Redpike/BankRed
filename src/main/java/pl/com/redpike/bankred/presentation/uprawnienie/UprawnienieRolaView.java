package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.control.uprawnienie.UprawnieniePresenter;

/**
 * Created by Redpike
 */
public class UprawnienieRolaView extends VerticalLayout {

    private UprawnieniePresenter uprawnieniePresenter;
    private UprawnieniePage uprawnieniePage;

    private MVerticalLayout verticalLayout;
    private Label label;

    public UprawnienieRolaView(UprawnieniePresenter uprawnieniePresenter, UprawnieniePage uprawnieniePage) {
        this.uprawnieniePresenter = uprawnieniePresenter;
        this.uprawnieniePage = uprawnieniePage;

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
