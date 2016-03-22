package ru.bona.guice.vaadin.mvp.addressbook.ui.list;

import com.google.code.vaadin.application.uiscope.UIScoped;
import com.google.code.vaadin.mvp.AbstractView;
import com.vaadin.data.Item;
import com.vaadin.ui.VerticalSplitPanel;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;

import javax.inject.Inject;
import java.util.Collection;

/*
 * ListViewImpl - is the implementation of ListView and receives
 * the calls from ListPresenter.
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
@UIScoped
public class ListViewImpl extends AbstractView implements ListView {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -5733772238853887276L;

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    /*
     * Instance<PersonForm> is used here so the personForm and personList won't
     * be injected until needed (Lazy initialization). They are both session
     * scoped so personForm will always return the same instance (for the
     * session). If PersonForm would be Dependent (default)-scoped and
     * Instance<PersonForm> was still used, a new instance would be created
     * every time personForm was called.
     */
    @Inject
    private PersonForm personForm;

    @Inject
    private PersonList personList;

    private PersonDto currentPersonDto;

	/*===========================================[ INTERFACE METHODS ]============*/

    @Override
    public void setPersonList(Collection<PersonDto> people) {
        personList.setPersonList(people);
    }

    @Override
    public void showSelectedPersonDetails() {
        Item item = personList.getSelectedItem();
        personForm.setItemDataSource(item);
        personForm.setReadOnly(true);
    }

    @Override
    public void editNewContact(PersonDto person) {
        personList.setValue(null);
        personForm.editNewContact(person);
    }

    @Override
    public void setCityOptions(Collection<String> cityOptions) {
        personForm.setCityOptions(cityOptions);
    }

    @Override
    public void addContactToList(PersonDto person) {
        personList.addContactToList(person);
    }

    @Override
    public void selectPerson(PersonDto person) {
        personList.setValue(person);
        currentPersonDto = person;
        personForm.setReadOnly(true);
    }

    @Override
    public void editSelectedPerson() {
        personForm.setReadOnly(false);
    }

    @Override
    public void applyFilter(SearchDto searchFilter) {
        personList.applyFilter(searchFilter);
    }

    @Override
    public void cancelEditing() {
        personForm.cancel();
    }

    /*
     * View is constructed in the initView-method. Injected resources are not
     * available in the class constructor so this is the right place to
     * initialize.
     */
    @Override
    protected void initView() {
        addStyleName("view");
        setSizeFull();

        VerticalSplitPanel mainPanel = new VerticalSplitPanel();
        setCompositionRoot(mainPanel);

        personList.init();
        mainPanel.setFirstComponent(personList);

        personForm.init();
        mainPanel.setSecondComponent(personForm);
        mainPanel.setSplitPosition(40);
    }
}