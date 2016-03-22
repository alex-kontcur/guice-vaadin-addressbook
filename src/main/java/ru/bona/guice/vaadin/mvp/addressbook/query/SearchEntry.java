package ru.bona.guice.vaadin.mvp.addressbook.query;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.SearchId;

import javax.persistence.*;

/**
 * SearchEntry
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@Entity
public class SearchEntry {

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @EmbeddedId
    private SearchId searchId;

    @Column
    private String term;

    @Column
    private String property;

    @Column
    private String searchName;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public SearchEntry() {
    }

    public SearchEntry(SearchId searchId, String term, String property, String searchName) {
        this.searchId = searchId;

        this.term = term;
        this.property = property;
        this.searchName = searchName;
    }

    /*===========================================[ CLASS METHODS ]================*/

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
