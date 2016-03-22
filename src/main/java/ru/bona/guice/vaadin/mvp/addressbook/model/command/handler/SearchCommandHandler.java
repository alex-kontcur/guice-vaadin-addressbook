package ru.bona.guice.vaadin.mvp.addressbook.model.command.handler;

import com.google.code.axonguice.commandhandling.annotation.CommandHandlerComponent;
import com.google.inject.Inject;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import ru.bona.guice.vaadin.mvp.addressbook.model.Search;
import ru.bona.guice.vaadin.mvp.addressbook.model.command.CreateSearchCommand;

/**
 * SearchCommandHandler
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@CommandHandlerComponent
public class SearchCommandHandler {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private Repository<Search> repository;

    /*===========================================[ CLASS METHODS ]================*/

    @CommandHandler
    protected void handleCreateCompany(CreateSearchCommand command) {
        Search search = new Search(command.getSearchId(), command.getTerm(), command.getProperty(), command.getSearchName());
        repository.add(search);
    }
}
