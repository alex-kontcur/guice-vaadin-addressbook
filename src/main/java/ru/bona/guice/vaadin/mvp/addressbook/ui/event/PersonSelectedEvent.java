package ru.bona.guice.vaadin.mvp.addressbook.ui.event;

import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;

/**
 * PersonSelectedEvent
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
public class PersonSelectedEvent {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonDto personDto;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public PersonSelectedEvent(PersonDto personDto) {
        this.personDto = personDto;
    }

	/*===========================================[ GETTER/SETTER ]================*/

    public PersonDto getPersonDto() {
        return personDto;
    }
}
