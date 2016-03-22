package ru.bona.guice.vaadin.mvp.addressbook.query;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * CityEntry
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@Entity
public class CityEntry {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @EmbeddedId
    private CityId cityId;

    @Column
    private String name;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public CityEntry() {
    }

    public CityEntry(CityId cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

	/*===========================================[ GETTER/SETTER ]================*/

    public String getName() {
        return name;
    }

    public CityId getId() {
        return cityId;
    }
}
