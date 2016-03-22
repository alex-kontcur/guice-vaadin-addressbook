package ru.bona.guice.vaadin.mvp.addressbook.util;

import com.vaadin.data.util.converter.StringToIntegerConverter;

import java.util.Locale;

/**
 * AddressBookStringToIntConverter
 *
 * @author Kontsur Alex (bona)
 * @since 05.03.13
 */
public class AddressBookStringToIntConverter extends StringToIntegerConverter {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 2570896314885216884L;

    /*===========================================[ CLASS METHODS ]================*/

    @Override
    public String convertToPresentation(Integer value, Locale locale)
            throws ConversionException {
        if (value == null) {
            return null;
        }

        return String.valueOf(value);
    }
}
