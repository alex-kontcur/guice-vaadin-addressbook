package ru.bona.guice.vaadin.mvp.addressbook.model;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import ru.bona.guice.vaadin.mvp.addressbook.model.event.CityCreatedEvent;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;

/**
 * City
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
public class City extends AbstractAnnotatedAggregateRoot {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -7215272333120924942L;

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @AggregateIdentifier
    private CityId cityId;
    private String name;

	/*===========================================[ CONSTRUCTORS ]=================*/

    protected City() {
    }

    public City(CityId cityId, String name) {
        apply(new CityCreatedEvent(cityId, name));
    }

	/*===========================================[ CLASS METHODS ]================*/

    @EventHandler
    public void onCityCreated(CityCreatedEvent event) {
        cityId = event.getCityId();
        name = event.getName();
    }

    @Override
    public CityId getIdentifier() {
        return cityId;
    }

    public String getName() {
        return name;
    }
}
