package ru.bona.guice.vaadin.mvp.addressbook.query.listener;

import com.google.code.axonguice.eventhandling.annotation.EventHandlerComponent;
import com.google.inject.Inject;
import org.axonframework.eventhandling.annotation.EventHandler;
import ru.bona.guice.vaadin.mvp.addressbook.model.event.CityCreatedEvent;
import ru.bona.guice.vaadin.mvp.addressbook.query.CityEntry;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.CityRepository;

/**
 * CityListener
 *
 * @author Kontsur Alex (bona)
 * @since 01.03.13
 */
@EventHandlerComponent
public class CityListener {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private CityRepository cityRepository;

    /*===========================================[ CLASS METHODS ]================*/

    @EventHandler
    protected void on(CityCreatedEvent event) {
        CityEntry cityEntry = new CityEntry(event.getCityId(), event.getName());
        cityRepository.save(cityEntry);
    }
}
