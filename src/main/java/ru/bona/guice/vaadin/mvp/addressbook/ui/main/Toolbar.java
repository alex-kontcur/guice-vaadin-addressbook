package ru.bona.guice.vaadin.mvp.addressbook.ui.main;

import com.google.code.vaadin.components.preconfigured.Preconfigured;
import com.google.code.vaadin.mvp.ViewComponent;
import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.HelpEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.NewContactEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.NewSearchEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.ShareEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.localization.Lang;

import javax.inject.Inject;
import java.util.Locale;

/**
 * Toolbar
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
public class Toolbar extends ViewComponent {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 1247501862201197113L;

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    @Preconfigured(styleName = "toolbar", margin = true, spacing = true, width = 100.0f, widthUnits = Unit.PERCENTAGE)
    private HorizontalLayout layout;

    @Inject
    @Preconfigured(captionKey = "toolbar-addcontact")
    private Button addContactButton;

    @Inject
    @Preconfigured(captionKey = "toolbar-search")
    private Button searchButton;

    @Inject
    @Preconfigured(captionKey = "toolbar-share")
    private Button shareButton;

    @Inject
    @Preconfigured(captionKey = "toolbar-help")
    private Button helpButton;

    private ComboBox languageSelector;

    @Inject
    private Lang lang;

    /*===========================================[ CLASS METHODS ]================*/

    @Override
    public void initComponent() {
        //if (subject.hasPermission(ApplicationPermissions.CONTACTS_CREATE)) {
        addToolbarButton(addContactButton, "icons/32/document-add.png", new NewContactEvent());
        //}

        addToolbarButton(searchButton, "icons/32/folder-add.png", new NewSearchEvent());
        addToolbarButton(shareButton, "icons/32/users.png", new ShareEvent());
        addToolbarButton(helpButton, "icons/32/help.png", new HelpEvent());

        languageSelector = new ComboBox("", Lang.getInstalledLocales());
        languageSelector.setImmediate(true);
        languageSelector.setNullSelectionAllowed(false);

        languageSelector.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Object value = event.getProperty().getValue();
                if (value instanceof Locale) {
                    UI.getCurrent().setLocale((Locale) value);
                }
            }
        });

        languageSelector.setValue(lang.current());

        layout.addComponent(languageSelector);

        //logout.addClickListener(new Button.ClickListener() {
        //    @Override
        //    public void buttonClick(ClickEvent event) {
        //        fireViewEvent(new LogoutRequestedEvent());
        //    }
        //});
        //layout.addComponent(logout);

        Embedded em = new Embedded("", new ThemeResource("images/logo.png"));
        layout.addComponent(em);
        layout.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
        layout.setExpandRatio(em, 1.0F);
        setCompositionRoot(layout);
    }

    private void addToolbarButton(Button button, String iconUrl, final Object uiEvent) {
        button.setIcon(new ThemeResource("../runo/" + iconUrl));
        button.setWidth(120.0f, Unit.PIXELS);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                fireViewEvent(uiEvent);
            }
        });
        layout.addComponent(button);
    }
}