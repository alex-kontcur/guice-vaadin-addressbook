package ru.bona.guice.vaadin.mvp.addressbook.model.command.handler;

import com.google.code.axonguice.commandhandling.annotation.CommandHandlerComponent;
import com.google.inject.Inject;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import ru.bona.guice.vaadin.mvp.addressbook.model.City;
import ru.bona.guice.vaadin.mvp.addressbook.model.command.CreateCityCommand;

/**
 * CityCommandHandler
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@CommandHandlerComponent
public class CityCommandHandler {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private Repository<City> repository;

    /*===========================================[ CLASS METHODS ]================*/

    @CommandHandler
    protected void handleCreateCompany(CreateCityCommand command) {
        City city = new City(command.getCityId(), command.getName());
        repository.add(city);
    }
}
