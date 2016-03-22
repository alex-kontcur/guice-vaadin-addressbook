package ru.bona.guice.vaadin.mvp.addressbook.ui.search;

import com.google.code.vaadin.mvp.View;
import com.google.inject.ImplementedBy;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;

/**
 * SearchView
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@ImplementedBy(SearchViewImpl.class)
public interface SearchView extends View {

	/*===========================================[ INTERFACE METHODS ]============*/

    void editNewSearchFilter(SearchDto searchFilter);
}
