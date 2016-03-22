package ru.bona.guice.vaadin.mvp.addressbook.model.command.handler;

import com.google.code.axonguice.commandhandling.annotation.CommandHandlerComponent;
import com.google.inject.Inject;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import ru.bona.guice.vaadin.mvp.addressbook.model.Person;
import ru.bona.guice.vaadin.mvp.addressbook.model.command.person.CreatePersonCommand;

/**
 * PersonCommandHandler
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@CommandHandlerComponent
public class PersonCommandHandler {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private Repository<Person> repository;

    /*===========================================[ CLASS METHODS ]================*/

    @CommandHandler
    protected void handleCreateCompany(CreatePersonCommand command) {
        Person person = new Person(
            command.getPersonId(), command.getFirstName(), command.getLastName(), command.getEmail(),
            command.getPhoneNumber(), command.getStreetAddress(), command.getPostalCode(), command.getCityId());

        repository.add(person);
    }
}
