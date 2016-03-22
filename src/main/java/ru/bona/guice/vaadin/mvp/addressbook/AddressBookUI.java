package ru.bona.guice.vaadin.mvp.addressbook;

import com.google.code.vaadin.application.ui.ScopedUI;
import com.google.code.vaadin.mvp.eventhandling.ViewEventPublisher;
import com.google.code.vaadin.mvp.eventhandling.events.LocaleChangedEvent;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import ru.bona.guice.vaadin.mvp.addressbook.ui.localization.Lang;
import ru.bona.guice.vaadin.mvp.addressbook.ui.main.MainView;

import javax.inject.Inject;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AddressBookUI
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
@Theme("contacts")
public class AddressBookUI extends ScopedUI {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 2647214791537002428L;

    public enum Views {LOGIN, MAIN}

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private Logger logger;

    @Inject
    private MainView mainView;

    @Inject
    private Lang lang;

    @Inject
    private ViewEventPublisher viewEventPublisher;

	/*===========================================[ CONSTRUCTORS ]=================*/
    @Override
    public void init(VaadinRequest request) {
        logger.log(Level.INFO, "initApplication()");

        lang.setLocale(Lang.EN_US);

        setContent(mainView);
        mainView.open();
    }

	/*===========================================[ CLASS METHODS ]================*/

    @Override
    public void setLocale(Locale locale) {
        lang.setLocale(locale);
        super.setLocale(locale);
        viewEventPublisher.publish(new LocaleChangedEvent(locale));
    }
}