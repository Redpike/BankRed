package pl.com.redpike.bankred.presentation.components.presenters;

import pl.com.redpike.bankred.presentation.components.views.View;

/**
 * Created by Redpike
 */
public abstract class AbstractPresenter<VIEW extends View> implements Presenter {

    private VIEW view;

    public AbstractPresenter() {

    }

    public VIEW getView() {
        return this.view;
    }

    public void setView(View view) {
        this.view = (VIEW) view;
    }
}
