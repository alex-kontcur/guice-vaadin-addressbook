package ru.bona.guice.vaadin.mvp.addressbook.model.event;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

/**
 * PersonCreatedEvent
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
public class PersonCreatedEvent {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonId personId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private Integer postalCode;
    private CityId cityId;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public PersonCreatedEvent(PersonId personId, String firstName, String lastName, String email, String phoneNumber, 
                              String streetAddress, Integer postalCode, CityId cityId) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.cityId = cityId;
    }

	/*===========================================[ GETTER/SETTER ]================*/

    public PersonId getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public CityId getCityId() {
        return cityId;
    }
}