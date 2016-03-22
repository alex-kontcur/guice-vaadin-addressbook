package ru.bona.guice.vaadin.mvp.addressbook.ui.event;

import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;

/**
 * SaveSearchEvent
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
public class SaveSearchEvent {
    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private SearchDto searchDto;

    /*===========================================[ CONSTRUCTORS ]=================*/

    public SaveSearchEvent(SearchDto searchDto) {
        this.searchDto = searchDto;
    }

    /*===========================================[ CLASS METHODS ]================*/

    public SearchDto getSearchDto() {
        return searchDto;
    }
}
