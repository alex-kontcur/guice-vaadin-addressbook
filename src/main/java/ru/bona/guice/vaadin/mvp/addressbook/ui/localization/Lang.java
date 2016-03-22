package ru.bona.guice.vaadin.mvp.addressbook.ui.localization;

import com.google.code.vaadin.application.uiscope.UIScoped;
import com.google.code.vaadin.localization.ResourceBundleProvider;
import com.google.code.vaadin.localization.TextBundle;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Lang
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
@UIScoped
public class Lang implements TextBundle, Serializable {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -6743885740548179919L;

    public static final Locale EN_US = new Locale("en", "US");
    public static final Locale RU_RU = new Locale("ru", "RU");

    public static Collection<Locale> getInstalledLocales() {
        return Arrays.asList(EN_US, RU_RU);
    }

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private ResourceBundleProvider resourceBundleProvider;

    private ResourceBundle resourceBundle;
    private Locale current;

    /*===========================================[ CLASS METHODS ]================*/

    @PostConstruct
    void postConstruct(){
        setLocale(EN_US);
    }

    public void setLocale(Locale locale) {
        if (locale == null) {
            return;
        }
        current = locale;
        resourceBundle = getBundle(locale);
    }

    private ResourceBundle getBundle(Locale locale) {
        return resourceBundleProvider.getBundle("AddressBookLang", locale);
    }

    /*===========================================[ INTERFACE METHODS ]============*/

    public Locale current() {
        return current;
    }

    @Override
    public String getText(String key, Object... params) {
        if (resourceBundle == null) {
            return "NO Bundle";
        }
        return MessageFormat.format(resourceBundle.getString(key), params);
    }
}