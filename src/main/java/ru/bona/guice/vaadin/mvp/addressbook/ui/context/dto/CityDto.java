package ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;

import java.io.Serializable;

/**
 * CitiDto
 *
 * @author Kontsur Alex
 * @since 30.01.13
 */
public class CityDto implements Serializable {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -4155659418995992167L;

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private CityId cityId;
    private String name;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public CityDto(CityId cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

    /*===========================================[ CLASS METHODS ]================*/

    public CityId getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
