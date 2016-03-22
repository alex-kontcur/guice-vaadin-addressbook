package ru.bona.guice.vaadin.mvp.addressbook.model.command;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;

/**
 * CreateCityCommand
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
public class CreateCityCommand {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private CityId cityId;
    private String name;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public CreateCityCommand(CityId cityId, String name) {
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
