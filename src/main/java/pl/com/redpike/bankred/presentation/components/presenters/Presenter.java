package pl.com.redpike.bankred.presentation.components.presenters;

import pl.com.redpike.bankred.presentation.components.views.View;

/**
 * Created by Redpike
 */
public interface Presenter {

    View getView();

    void setView(View view);
}
