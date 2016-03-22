package ru.bona.guice.vaadin.mvp.addressbook.ui.event;

import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;

/**
 * SearchEvent
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
public class SearchEvent {
    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private SearchDto searchDto;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public SearchEvent(SearchDto searchDto) {
        this.searchDto = searchDto;
    }

    /*===========================================[ CLASS METHODS ]================*/

    public SearchDto getSearchDto() {
        return searchDto;
    }
}
