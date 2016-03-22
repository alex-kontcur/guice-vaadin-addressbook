package ru.bona.guice.vaadin.mvp.addressbook.ui.main;

import com.google.code.vaadin.mvp.View;
import com.google.inject.ImplementedBy;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;

/**
 * MainView
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@ImplementedBy(MainViewImpl.class)
public interface MainView extends View {

	/*===========================================[ INTERFACE METHODS ]============*/

    String resourceName = "/addressbook/main-view";

    void setView(Class<? extends View> viewClass, boolean selectInNavigationTree);

    void showHelpWindow();

    void showShareWindow();

    void addSearchToTree(SearchDto searchFilter);
}
