package pl.com.redpike.bankred.presentation.components.windows;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * Created by Redpike
 */
public enum WindowOpenerImpl implements WindowOpener {
    INSTANCE;

    @Override
    public void openWindow(Window window) {
        if (!UI.getCurrent().getWindows().contains(window))
            UI.getCurrent().addWindow(window);
    }
}
