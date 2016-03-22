package ru.bona.guice.vaadin.mvp.addressbook.model.command.person;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

/**
 * ChangePersonFirstNameCommand
 *
 * @author Kontsur Alex (bona)
 * @since 28.02.13
 */
public class ChangePersonFirstNameCommand {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonId personId;
    private String firstName;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public ChangePersonFirstNameCommand(Object personId, Object firstName) {
        this.personId = (PersonId) personId;
        this.firstName = (String) firstName;
    }

    /*===========================================[ GETTER/SETTER ]================*/

    public PersonId getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }
}
