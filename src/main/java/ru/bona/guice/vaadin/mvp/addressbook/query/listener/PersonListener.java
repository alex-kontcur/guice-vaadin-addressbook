package ru.bona.guice.vaadin.mvp.addressbook.query.listener;

import com.google.code.axonguice.eventhandling.annotation.EventHandlerComponent;
import com.google.inject.Inject;
import org.axonframework.eventhandling.annotation.EventHandler;
import ru.bona.guice.vaadin.mvp.addressbook.model.event.PersonCreatedEvent;
import ru.bona.guice.vaadin.mvp.addressbook.query.CityEntry;
import ru.bona.guice.vaadin.mvp.addressbook.query.PersonEntry;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.CityRepository;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.PersonRepository;

/**
 * PersonListener
 *
 * @author Kontsur Alex (bona)
 * @since 01.03.13
 */
@EventHandlerComponent
public class PersonListener {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private PersonRepository personRepository;

    @Inject
    private CityRepository cityRepository;

    /*===========================================[ CLASS METHODS ]================*/

    @EventHandler
    protected void on(PersonCreatedEvent event) {
        CityEntry cityEntry = cityRepository.findOne(event.getCityId());
        PersonEntry personEntry = new PersonEntry(
            event.getPersonId(),
            event.getFirstName(),
            event.getLastName(),
            event.getEmail(),
            event.getPhoneNumber(),
            event.getStreetAddress(),
            event.getPostalCode(),
            cityEntry
        );

        personRepository.save(personEntry);
    }
}
