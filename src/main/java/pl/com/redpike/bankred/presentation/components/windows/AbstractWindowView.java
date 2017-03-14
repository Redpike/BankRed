package pl.com.redpike.bankred.presentation.components.windows;

import com.vaadin.ui.Window;
import pl.com.redpike.bankred.presentation.components.presenters.Presenter;
import pl.com.redpike.bankred.presentation.components.views.View;

/**
 * Created by Redpike
 */
public abstract class AbstractWindowView<PRESENTER extends Presenter> extends Window implements View {

    private final PRESENTER presenter;
    private final WindowOpener windowOpener;

    public AbstractWindowView(PRESENTER presenter, WindowOpener windowOpener) {
        this.presenter = presenter;
        this.presenter.setView(this);
        this.windowOpener = windowOpener;
        this.setImmediate(true);
    }

    public void showWindow() {
        windowOpener.openWindow(this);
    }

    @Override
    public PRESENTER getPresenter() {
        return presenter;
    }

    public WindowOpener getWindowOpener() {
        return windowOpener;
    }
}
