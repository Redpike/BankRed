package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.ui.TabSheet;
import pl.com.redpike.bankred.control.uprawnienie.UprawnieniePresenter;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;
import pl.com.redpike.bankred.util.styles.Styles;

/**
 * Created by Redpike
 */
public class UprawnienieTabView extends AbstractView<UprawnieniePresenter> {

    private UprawnieniePresenter uprawnieniePresenter;
    private UprawnieniePage uprawnieniePage;

    private TabSheet tabSheet;
    private UprawnienieView uprawnienieView;
    private UprawnienieRolaView uprawnienieRolaView;

    public UprawnienieTabView(UprawnieniePresenter uprawnieniePresenter, UprawnieniePage uprawnieniePage) {
        super(uprawnieniePresenter);
        this.uprawnieniePage = uprawnieniePage;

        initComponents();
        initLayout();
    }

    private void initComponents() {
        uprawnienieView = new UprawnienieView(uprawnieniePresenter, uprawnieniePage);
        uprawnienieRolaView = new UprawnienieRolaView(uprawnieniePresenter, uprawnieniePage);

        tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        tabSheet.addStyleName(Styles.TAB_SHEET_PADDING);
        tabSheet.addTab(uprawnienieView);
        tabSheet.addTab(uprawnienieRolaView);
    }

    private void initLayout() {
        setContent(tabSheet);
    }

    public UprawnienieView getUprawnienieView() {
        return uprawnienieView;
    }

    public UprawnienieRolaView getUprawnienieRolaView() {
        return uprawnienieRolaView;
    }

    @Override
    public UprawnieniePresenter getPresenter() {
        return uprawnieniePresenter;
    }
}
