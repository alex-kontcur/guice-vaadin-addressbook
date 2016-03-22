package ru.bona.guice.vaadin.mvp.addressbook.ui.search;

import com.google.code.vaadin.components.preconfigured.Preconfigured;
import com.google.code.vaadin.mvp.AbstractView;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.SaveSearchEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.SearchEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.list.PersonList;

import javax.inject.Inject;

/**
 * SearchViewImpl
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@SuppressWarnings("serial")
public class SearchViewImpl extends AbstractView implements SearchView {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    @Preconfigured(captionKey = "searchview-searchterm")
    private TextField searchTerm;

    @Inject
    @Preconfigured(captionKey = "searchview-fieldtosearch")
    private NativeSelect fieldToSearch;

    @Inject
    @Preconfigured(captionKey = "searchview-savesearch", immediate = true, implementation = CheckBox.class)
    private Button saveSearch;

    @Inject
    @Preconfigured(captionKey = "searchview-searchname")
    private TextField searchName;

    private SearchDto searchFilter;
    private Panel mainPanel;
    private Button searchButton;

	/*===========================================[ CLASS METHODS ]================*/

    @Override
    protected void localize() {
        mainPanel.setCaption(getText("searchview-caption"));
        fieldToSearch.setCaption(getText("searchview-fieldtosearch"));
        searchTerm.setCaption(getText("searchview-searchterm"));
        saveSearch.setCaption(getText("searchview-savesearch"));
        searchName.setCaption(getText("searchview-searchname"));
        searchButton.setCaption(getText("searchview-search"));
        constructFieldToSearch();
    }

	/*===========================================[ INTERFACE METHODS ]============*/

    @Override
    public void editNewSearchFilter(SearchDto searchFilter) {
        this.searchFilter = searchFilter;
        searchTerm.setPropertyDataSource(new MethodProperty<String>(searchFilter, SearchDto.Fields.term.name()));
        fieldToSearch.setPropertyDataSource(new MethodProperty<String>(searchFilter, SearchDto.Fields.property.name()));
        searchName.setPropertyDataSource(new MethodProperty<String>(searchFilter, SearchDto.Fields.searchName.name()));
        saveSearch.setEnabled(true);
    }

    @Override
    protected void initView() {
        mainPanel = new Panel();
        mainPanel.setCaption(getText("searchview-caption"));
        FormLayout formLayout = new FormLayout();

        setCompositionRoot(mainPanel);

        addStyleName("view");
        setSizeFull();

        searchTerm.setNullRepresentation("");
        formLayout.addComponent(searchTerm);

        constructFieldToSearch();
        formLayout.addComponent(fieldToSearch);

        saveSearch.setEnabled(true);
        saveSearch.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                searchName.setVisible(saveSearch.isEnabled());
            }
        });

        formLayout.addComponent(saveSearch);

        searchName.setNullRepresentation("");
        formLayout.addComponent(searchName);

        searchButton = new Button(getText("searchview-search"), new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                performSearch();
            }
        });
        formLayout.addComponent(searchButton);

        mainPanel.setContent(formLayout);
    }

    private void constructFieldToSearch() {
        Object selected = fieldToSearch.getValue();
        fieldToSearch.removeAllItems();
        for (int i = 0; i < PersonList.NATURAL_COL_ORDER.length; i++) {
            fieldToSearch.addItem(PersonList.NATURAL_COL_ORDER[i]);

            String header = getText("person-" + PersonList.NATURAL_COL_ORDER[i]);
            fieldToSearch.setItemCaption(PersonList.NATURAL_COL_ORDER[i], header);
        }
        fieldToSearch.setNullSelectionAllowed(false);
        if (selected != null) {
            fieldToSearch.setValue(selected);
        }
    }

    @SuppressWarnings("MethodOnlyUsedFromInnerClass")
    private void performSearch() {
        String searchTerm = searchFilter.getTerm();
        if ((searchTerm == null) || searchTerm.isEmpty()) {
            String errorText = getText("searchview-error-termempty");
            Notification.show(errorText, Notification.Type.WARNING_MESSAGE);
        } else if (saveSearch.isEnabled()) {
            if (searchFilter.getSearchName() == null || searchFilter.getSearchName().isEmpty()) {
                String errorText = getText("searchview-error-filternameempty");
                Notification.show(errorText, Notification.Type.WARNING_MESSAGE);
            } else {
                fireViewEvent(new SaveSearchEvent(searchFilter));
            }
        } else {
            fireViewEvent(new SearchEvent(searchFilter));
        }
    }
}
