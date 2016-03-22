package ru.bona.guice.vaadin.mvp.addressbook.model.command.person;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

/**
 * ChangePersonLastNameCommand
 *
 * @author Kontsur Alex (bona)
 * @since 28.02.13
 */
public class ChangePersonLastNameCommand {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonId personId;
    private String lastName;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public ChangePersonLastNameCommand(Object personId, Object lastName) {
        this.personId = (PersonId) personId;
        this.lastName = (String) lastName;
    }

    /*===========================================[ GETTER/SETTER ]================*/

    public PersonId getPersonId() {
        return personId;
    }

    public String getLastName() {
        return lastName;
    }
}
