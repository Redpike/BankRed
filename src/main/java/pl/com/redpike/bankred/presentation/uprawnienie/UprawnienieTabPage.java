package pl.com.redpike.bankred.presentation.uprawnienie;

import com.vaadin.ui.TabSheet;
import pl.com.redpike.bankred.control.uprawnienie.UprawnieniePresenter;
import pl.com.redpike.bankred.presentation.components.views.AbstractView;

/**
 * Created by Redpike
 */
public class UprawnienieTabPage extends AbstractView<UprawnieniePresenter> {

    private UprawnieniePresenter uprawnieniePresenter;
    private UprawnienieView uprawnienieView;

    private TabSheet tabSheet;
    private UprawnieniePage uprawnieniePage;
    private UprawnienieRolaPage uprawnienieRolaPage;

    public UprawnienieTabPage(UprawnieniePresenter uprawnieniePresenter, UprawnienieView uprawnienieView) {
        super(uprawnieniePresenter);
        this.uprawnienieView = uprawnienieView;

        initComponents();
        initLayout();
    }

    private void initComponents() {
        uprawnieniePage = new UprawnieniePage(uprawnieniePresenter, uprawnienieView);
        uprawnienieRolaPage = new UprawnienieRolaPage(uprawnieniePresenter, uprawnienieView);

        tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        tabSheet.addTab(uprawnieniePage);
        tabSheet.addTab(uprawnienieRolaPage);
    }

    private void initLayout() {
        setContent(tabSheet);
    }

    public UprawnieniePage getUprawnieniePage() {
        return uprawnieniePage;
    }

    public UprawnienieRolaPage getUprawnienieRolaPage() {
        return uprawnienieRolaPage;
    }

    @Override
    public UprawnieniePresenter getPresenter() {
        return uprawnieniePresenter;
    }
}
