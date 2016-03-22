package ru.bona.guice.vaadin.mvp.addressbook.model.command.person;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

/**
 * ChangePersonEmailCommand
 *
 * @author Kontsur Alex (bona)
 * @since 28.02.13
 */
public class ChangePersonEmailCommand {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonId personId;
    private String email;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public ChangePersonEmailCommand(Object personId, Object email) {
        this.personId = (PersonId) personId;
        this.email = (String) email;
    }

    /*===========================================[ GETTER/SETTER ]================*/

    public PersonId getPersonId() {
        return personId;
    }

    public String getEmail() {
        return email;
    }
}
