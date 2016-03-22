package ru.bona.guice.vaadin.mvp.addressbook.ui.search;

import com.google.code.vaadin.mvp.AbstractPresenter;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;

/**
 * SearchPresenter
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@SuppressWarnings("serial")
public class SearchPresenter extends AbstractPresenter<SearchView> {

	/*===========================================[ INTERFACE METHODS ]============*/

    @Override
    protected void initPresenter() {

    }

    /*
     * Every time the SearchView is accessed (MainViewImpl calls
     * SearchViewImpl.openView(); that is) the Presenter is automatically
     * informed, a new SearchFilter is instantiated and passed to the view. View
     * uses the new SearchFilter to re-initialize the search-form.
     */
    @Override
    public void viewOpened() {
        SearchDto searchDto = new SearchDto();
        searchDto.setProperty(PersonDto.Fields.lastName.name());
        view.editNewSearchFilter(searchDto);
    }
}
