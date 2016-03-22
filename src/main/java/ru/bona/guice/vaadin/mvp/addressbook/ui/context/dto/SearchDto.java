package ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto;

import ru.bona.guice.vaadin.mvp.addressbook.model.identity.SearchId;

import java.io.Serializable;

/**
 * SearchDto - dto search filter object for ui level
 *
 * @author Kontsur Alex
 * @since 29.01.13
 */
public class SearchDto implements Serializable {

    /*===========================================[ ENUMERATIONS ]=================*/

    @SuppressWarnings({"EnumeratedConstantNamingConvention", "InnerClassFieldHidesOuterClassField"})
    public enum Fields {
        searchId, term, property, searchName
    }

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 4729473300186273986L;

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private SearchId searchId;
    private String term;
    private String property;
    private String searchName;

	/*===========================================[ CLASS METHODS ]================*/

    @Override
    public String toString() {
        return searchName;
    }

    public boolean isPersistent() {
        return searchId != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }
        SearchDto searchDto = (SearchDto) obj;
        return searchId == null ? obj == this : searchId.equals(searchDto.getSearchId());
    }

    @Override
    public int hashCode() {
        return searchId == null ? super.hashCode() : searchId.hashCode();
    }

	/*===========================================[ GETTER/SETTER ]================*/

    public SearchId getSearchId() {
        return searchId;
    }

    public void setSearchId(SearchId searchId) {
        this.searchId = searchId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

}
