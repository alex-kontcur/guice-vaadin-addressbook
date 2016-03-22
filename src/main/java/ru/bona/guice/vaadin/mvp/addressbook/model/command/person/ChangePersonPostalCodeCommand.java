package ru.bona.guice.vaadin.mvp.addressbook.model.command.person;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

/**
 * ChangePersonPostalCodeCommand
 *
 * @author Kontsur Alex (bona)
 * @since 28.02.13
 */
public class ChangePersonPostalCodeCommand {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonId personId;
    private String postalCode;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public ChangePersonPostalCodeCommand(Object personId, Object postalCode) {
        this.personId = (PersonId) personId;
        this.postalCode = (String) postalCode;
    }

    /*===========================================[ GETTER/SETTER ]================*/

    public PersonId getPersonId() {
        return personId;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
