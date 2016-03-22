package ru.bona.guice.vaadin.mvp.addressbook.ui.event;

import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;

/**
 * SavePersonEvent
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
public class SavePersonEvent {
    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonDto personDto;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public SavePersonEvent(PersonDto personDto) {
        this.personDto = personDto;
    }

    /*===========================================[ CLASS METHODS ]================*/

    public PersonDto getPersonDto() {
        return personDto;
    }

}
