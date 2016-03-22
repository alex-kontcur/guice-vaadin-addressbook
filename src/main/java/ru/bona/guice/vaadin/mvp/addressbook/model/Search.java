package ru.bona.guice.vaadin.mvp.addressbook.model;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import ru.bona.guice.vaadin.mvp.addressbook.model.event.SearchCreatedEvent;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.SearchId;

/**
 * Search
 *
 * @author Kontsur Alex
 * @since 29.01.13
 */
public class Search extends AbstractAnnotatedAggregateRoot {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 6228057465535394910L;

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @AggregateIdentifier
    private SearchId searchId;

    private String searchName;
    private String property;
    private String term;

	/*===========================================[ CONSTRUCTORS ]=================*/

    protected  Search() {
    }

    public Search(SearchId searchId, String term, String property, String searchName) {
        apply(new SearchCreatedEvent(searchId, term, property, searchName));
    }

	/*===========================================[ CLASS METHODS ]================*/

    @EventHandler
    public void onSearchCreated(SearchCreatedEvent event) {
        searchId = event.getSearchId();
        property = event.getProperty();
        searchName = event.getSearchName();
        term = event.getTerm();
    }

    @Override
    public SearchId getIdentifier() {
        return searchId;
    }
}
