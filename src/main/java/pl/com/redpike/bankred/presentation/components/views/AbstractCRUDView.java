package pl.com.redpike.bankred.presentation.components.views;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;
import pl.com.redpike.bankred.presentation.components.CRUDButtonLayout;
import pl.com.redpike.bankred.presentation.components.presenters.Presenter;

/**
 * Created by Redpike
 */
public abstract class AbstractCRUDView<PRESENTER extends Presenter, ENTITY> extends AbstractView {

    protected MVerticalLayout verticalLayout;
    protected CRUDButtonLayout crudButtonLayout;
    protected MTable<ENTITY> table;

    public AbstractCRUDView(PRESENTER presenter) {
        super(presenter);

        initComponents();
        initLayout();
        addListeners();
    }

    protected void initComponents() {
        crudButtonLayout = new CRUDButtonLayout();
        crudButtonLayout.getEditButton().setEnabled(false);
        crudButtonLayout.getDeleteButton().setEnabled(false);

        initTable();
        verticalLayout = new MVerticalLayout()
                .withMargin(true)
                .withSpacing(true)
                .withFullWidth()
                .with(crudButtonLayout, table);
    }

    protected abstract void initLayout();

    private void addListeners() {
        table.addValueChangeListener(event -> {
            if (event.getProperty().getValue() != null) {
                crudButtonLayout.getEditButton().setEnabled(true);
                crudButtonLayout.getDeleteButton().setEnabled(true);
            } else {
                crudButtonLayout.getEditButton().setEnabled(false);
                crudButtonLayout.getDeleteButton().setEnabled(false);
            }
        });
    }

    protected abstract void initTable();

    protected abstract void refreshTable();
}
