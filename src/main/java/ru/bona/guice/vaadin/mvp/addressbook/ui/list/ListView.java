package ru.bona.guice.vaadin.mvp.addressbook.ui.list;

import com.google.code.vaadin.mvp.View;
import com.google.inject.ImplementedBy;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;

import java.util.Collection;

/**
 * ListView
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
@ImplementedBy(ListViewImpl.class)
public interface ListView extends View {

    void setPersonList(Collection<PersonDto> people);

    void showSelectedPersonDetails();

    void editNewContact(PersonDto person);

    void setCityOptions(Collection<String> cityOptions);

    void addContactToList(PersonDto person);

    void selectPerson(PersonDto person);

    void editSelectedPerson();

    void applyFilter(SearchDto searchFilter);

    void cancelEditing();
}
