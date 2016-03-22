package ru.bona.guice.vaadin.mvp.addressbook.model;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import ru.bona.guice.vaadin.mvp.addressbook.model.event.PersonCreatedEvent;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;

/**
 * Person
 *
 * @author Kontsur Alex (bona)
 * @since 28.01.13
 */
public class Person extends AbstractAnnotatedAggregateRoot {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 9032122222126367125L;

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @AggregateIdentifier
    private PersonId personId;

    private String phoneNumber;
    private CityId cityId;

    /*===========================================[ CONSTRUCTORS ]=================*/

    protected Person() {
    }

    public Person(PersonId personId, String firstName, String lastName, String email, String phoneNumber,
                  String streetAddress, Integer postalCode, CityId cityId) {
        apply(new PersonCreatedEvent(personId, firstName, lastName, email, phoneNumber, streetAddress, postalCode, cityId));
    }

    /*===========================================[ CLASS METHODS ]================*/

    @EventHandler
    public void onPersonCreated(PersonCreatedEvent event) {
        phoneNumber = event.getPhoneNumber();
        personId = event.getPersonId();
        cityId = event.getCityId();
    }

    @Override
    public PersonId getIdentifier() {
        return personId;
    }

}