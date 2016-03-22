package ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

import java.io.Serializable;

/**
 * PersonDto - dto person object for ui level
 *
 * @author Kontsur Alex
 * @since 29.01.13
 */
public class PersonDto implements Serializable {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 1870858128021496749L;

    /*===========================================[ ENUMERATIONS ]=================*/

    @SuppressWarnings({"EnumeratedConstantNamingConvention", "InnerClassFieldHidesOuterClassField"})
    public enum Fields {
        id, firstName, lastName, email, phoneNumber, streetAddress, postalCode, city
    }

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonId id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private Integer postalCode;
    private String city;

    /*===========================================[ CLASS METHODS ]================*/

    public boolean isPersistent() {
        return id != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }
        PersonDto personDto = (PersonDto) obj;
        return id == null ? obj == this : id.equals(personDto.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    /*===========================================[ GETTER/SETTER ]================*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public PersonId getId() {
        return id;
    }

    public void setId(PersonId id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
