package ru.bona.guice.vaadin.mvp.addressbook.model.command.person;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

/**
 * ChangePersonPhoneNumberCommand
 *
 * @author Kontsur Alex (bona)
 * @since 28.02.13
 */
public class ChangePersonPhoneNumberCommand {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonId personId;
    private String phoneNumber;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public ChangePersonPhoneNumberCommand(Object personId, Object phoneNumber) {
        this.personId = (PersonId) personId;
        this.phoneNumber = (String) phoneNumber;
    }

    /*===========================================[ GETTER/SETTER ]================*/

    public PersonId getPersonId() {
        return personId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
