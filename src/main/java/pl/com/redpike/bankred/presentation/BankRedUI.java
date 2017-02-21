package pl.com.redpike.bankred.presentation;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import pl.com.redpike.bankred.business.uzytkownik.UzytkownikDAO;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

/**
 * Created by rs3 on 21.02.2017.
 */
@CDIUI("")
@Theme("valo")
public class BankRedUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;

    @EJB
    private UzytkownikDAO uzytkownikDAO;

    @Inject
    private Greeting greeting;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> layout.addComponent(new Label(greeting.getText() + " " + name.getValue())));

        uzytkownikDAO.findAll().forEach(uzytkownik -> layout.addComponent(new Label(uzytkownik.toString())));

        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }
}
