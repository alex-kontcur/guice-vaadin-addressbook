package ru.bona.guice.vaadin.mvp.addressbook.model.command;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.SearchId;

/**
 * CreateSearchCommand
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
public class CreateSearchCommand {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private SearchId searchId;

    private String searchName;
    private String property;
    private String term;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public CreateSearchCommand(SearchId searchId, String searchName, String property, String term) {
        this.searchId = searchId;
        this.searchName = searchName;
        this.property = property;
        this.term = term;
    }

	/*===========================================[ GETTER/SETTER ]================*/

    public SearchId getSearchId() {
        return searchId;
    }

    public String getSearchName() {
        return searchName;
    }

    public String getProperty() {
        return property;
    }

    public String getTerm() {
        return term;
    }
}
