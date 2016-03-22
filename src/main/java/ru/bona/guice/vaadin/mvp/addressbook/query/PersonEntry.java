package ru.bona.guice.vaadin.mvp.addressbook.query;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * PersonEntry
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@Entity
public class PersonEntry {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @EmbeddedId
    private PersonId personId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String streetAddress;

    @Column
    private Integer postalCode;

    @ManyToOne
    private CityEntry city;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public PersonEntry() {
    }

    public PersonEntry(PersonId personId, String firstName, String lastName, String email, String phoneNumber,
                       String streetAddress, Integer postalCode, CityEntry city) {

        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
    }

    /*===========================================[ CLASS METHODS ]================*/

    public boolean isPersistent() {
        return personId != null;
    }

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

    public CityEntry getCity() {
        return city;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(CityEntry city) {
        this.city = city;
    }

}
