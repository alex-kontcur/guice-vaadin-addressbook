package ru.bona.guice.vaadin.mvp.addressbook.model.event;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.SearchId;

/**
 * SearchCreatedEvent
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
public class SearchCreatedEvent {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private SearchId searchId;

    private String searchName;
    private String property;
    private String term;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public SearchCreatedEvent(SearchId searchId, String term, String property, String searchName) {
        this.searchId = searchId;
        this.term = term;
        this.property = property;
        this.searchName = searchName;
    }

    /*===========================================[ GETTER/SETTER ]================*/

    public SearchId getSearchId() {
        return searchId;
    }

    public String getTerm() {
        return term;
    }

    public String getProperty() {
        return property;
    }

    public String getSearchName() {
        return searchName;
    }
}