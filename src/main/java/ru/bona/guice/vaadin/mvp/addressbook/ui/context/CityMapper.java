package ru.bona.guice.vaadin.mvp.addressbook.ui.context;

import ru.bona.guice.vaadin.mvp.addressbook.query.CityEntry;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * CityMapper
 *
 * @author Kontsur Alex (bona)
 * @since 28.02.13
 */
@Singleton
public class CityMapper {

    /*===========================================[ CLASS METHODS ]================*/

    public List<String> buildCityDtoList(List<CityEntry> cityEntries) {
        List<String> out = new ArrayList<>();
        for (CityEntry cityEntry : cityEntries) {
            out.add(cityEntry.getName());
        }
        return out;
    }
}
