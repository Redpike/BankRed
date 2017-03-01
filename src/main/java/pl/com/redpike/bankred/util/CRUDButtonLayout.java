package pl.com.redpike.bankred.util;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * Created by Redpike
 */
public class CRUDButtonLayout extends HorizontalLayout {

    private MHorizontalLayout layout;
    private Panel panel;
    private MButton addButton;
    private MButton editButton;
    private MButton deleteButton;

    public CRUDButtonLayout() {
        initComponents();
        initLayout();
    }

    private void initComponents() {
        addButton = new MButton("Dodaj").withIcon(FontAwesome.PLUS_SQUARE_O);
        editButton = new MButton("Edytuj").withIcon(FontAwesome.EDIT);
        deleteButton = new MButton("Usuń").withIcon(FontAwesome.TRASH_O);

        layout = new MHorizontalLayout(addButton, editButton, deleteButton);
        panel = new Panel(" Akcje");
        panel.setIcon(FontAwesome.USER);
        panel.setCaption( "Akcje");
    }

    private void initLayout() {
        layout.setSpacing(true);
        layout.setMargin(true);

        panel.setContent(layout);
        panel.setWidth(100, Unit.PERCENTAGE);

        setWidth(100, Unit.PERCENTAGE);
        addComponent(panel);
    }

    public MButton getAddButton() {
        return addButton;
    }

    public MButton getEditButton() {
        return editButton;
    }

    public MButton getDeleteButton() {
        return deleteButton;
    }
}
