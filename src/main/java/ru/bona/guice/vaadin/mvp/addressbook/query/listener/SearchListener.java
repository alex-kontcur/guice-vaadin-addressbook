package ru.bona.guice.vaadin.mvp.addressbook.query.listener;

import com.google.code.axonguice.eventhandling.annotation.EventHandlerComponent;
import com.google.inject.Inject;
import org.axonframework.eventhandling.annotation.EventHandler;
import ru.bona.guice.vaadin.mvp.addressbook.model.event.SearchCreatedEvent;
import ru.bona.guice.vaadin.mvp.addressbook.query.SearchEntry;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.SearchRepository;

/**
 * SearchListener
 *
 * @author Kontsur Alex (bona)
 * @since 01.03.13
 */
@EventHandlerComponent
public class SearchListener {

    @Inject
    private SearchRepository searchRepository;

    @EventHandler
    protected void on(SearchCreatedEvent event) {
        SearchEntry searchEntry = new SearchEntry(event.getSearchId(), event.getTerm(), event.getProperty(), event.getSearchName());
        searchRepository.save(searchEntry);
    }
}
