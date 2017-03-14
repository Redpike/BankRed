package pl.com.redpike.bankred.presentation.components.views;

import com.vaadin.ui.Panel;
import pl.com.redpike.bankred.presentation.components.presenters.Presenter;

/**
 * Created by Redpike
 */
public abstract class AbstractView<PRESENTER extends Presenter> extends Panel implements View {

    private final PRESENTER presenter;

    public AbstractView(PRESENTER presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    public PRESENTER getPresenter() {
        return this.presenter;
    }
}
