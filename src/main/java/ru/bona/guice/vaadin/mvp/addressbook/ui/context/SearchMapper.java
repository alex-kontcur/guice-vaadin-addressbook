package ru.bona.guice.vaadin.mvp.addressbook.ui.context;

import ru.bona.guice.vaadin.mvp.addressbook.query.SearchEntry;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;

/**
 * SearchMapper - maps model SearchFilter to DTO SearchFilter and forth
 *
 * @author Kontsur Alex
 * @since 30.01.13
 */
public class SearchMapper {

	/*===========================================[ CLASS METHODS ]================*/

    public SearchDto buildSearchDto(SearchEntry searchEntry) {
        SearchDto searchDto = new SearchDto();
        searchDto.setProperty(searchEntry.getProperty());
        searchDto.setSearchName(searchEntry.getSearchName());
        searchDto.setTerm(searchEntry.getTerm());
        return searchDto;
    }
}