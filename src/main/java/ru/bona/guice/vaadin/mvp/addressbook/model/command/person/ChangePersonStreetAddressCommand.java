package ru.bona.guice.vaadin.mvp.addressbook.model.command.person;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

/**
 * ChangePersonStreetAddressCommand
 *
 * @author Kontsur Alex (bona)
 * @since 28.02.13
 */
public class ChangePersonStreetAddressCommand {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonId personId;
    private String streetAddress;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public ChangePersonStreetAddressCommand(Object personId, Object streetAddress) {
        this.personId = (PersonId) personId;
        this.streetAddress = (String) streetAddress;
    }

    /*===========================================[ GETTER/SETTER ]================*/

    public PersonId getPersonId() {
        return personId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }
}
