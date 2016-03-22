package ru.bona.guice.vaadin.mvp.addressbook.model.event;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;

/**
 * CityCreatedEvent
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
public class CityCreatedEvent {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private CityId cityId;
    private String name;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public CityCreatedEvent(CityId cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

	/*===========================================[ GETTER/SETTER ]================*/

    public CityId getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }
}