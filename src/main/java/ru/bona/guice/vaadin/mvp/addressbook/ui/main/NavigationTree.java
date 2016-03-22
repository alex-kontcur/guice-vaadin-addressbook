package ru.bona.guice.vaadin.mvp.addressbook.ui.main;

import com.google.code.vaadin.components.preconfigured.Preconfigured;
import com.google.code.vaadin.mvp.View;
import com.google.code.vaadin.mvp.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Tree;
import ru.bona.guice.vaadin.mvp.addressbook.query.SearchEntry;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.SearchRepository;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.SearchMapper;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.NewSearchEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.SearchEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.ShowAllEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.list.ListView;
import ru.bona.guice.vaadin.mvp.addressbook.ui.search.SearchView;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * NavigationTree
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@SuppressWarnings("serial")
public class NavigationTree extends ViewComponent {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final String SHOW_ALL = "show_all";
    private static final String NEW_SEARCH = "new_search";

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    @Preconfigured(nullSelectionAllowed = false, immediate = true)
    private Tree tree;

    @Inject
    private SearchRepository searchRepository;

    @Inject
    private SearchMapper searchMapper;

    private List<SearchDto> previousSearches;

    private final Property.ValueChangeListener listener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            Object itemId = event.getProperty().getValue();
            if (itemId != null) {
                if (itemId instanceof SearchDto) {
                    fireViewEvent(new SearchEvent((SearchDto) itemId));
                } else {
                    if (SHOW_ALL.equals(itemId)) {
                        fireViewEvent(new ShowAllEvent());
                    } else if (NEW_SEARCH.equals(itemId)) {
                        fireViewEvent(new NewSearchEvent());
                    }
                }
            }
        }
    };

    /*===========================================[ CLASS METHODS ]================*/

    @PostConstruct
    public void postConstruct() {
        previousSearches = new ArrayList<>();
    }

    @Override
    public void initComponent() {
        tree.setSelectable(true);

        tree.addItem(SHOW_ALL);
        tree.setItemCaption(SHOW_ALL, getText("navigation-showall"));
        tree.setChildrenAllowed(SHOW_ALL, false);

        tree.addItem(NEW_SEARCH);
        tree.setItemCaption(NEW_SEARCH, getText("navigation-search"));

        tree.addValueChangeListener(listener);
        setCompositionRoot(tree);
    }

    @Override
    protected void localize() {
        tree.removeItem(SHOW_ALL);
        tree.removeItem(NEW_SEARCH);

        tree.addItem(SHOW_ALL);
        tree.setItemCaption(SHOW_ALL, getText("navigation-showall"));
        tree.setChildrenAllowed(SHOW_ALL, false);

        tree.addItem(NEW_SEARCH);
        tree.setItemCaption(NEW_SEARCH, getText("navigation-search"));

        for (SearchDto previousSearch : previousSearches) {
            tree.removeItem(previousSearch);
        }

        List<SearchEntry> searchEntries = searchRepository.findAll();
        for (SearchEntry searchEntry : searchEntries) {
            addSearchToTree(searchMapper.buildSearchDto(searchEntry));
        }
    }

    public void addSearchToTree(SearchDto searchFilter) {
        previousSearches.add(searchFilter);
        Item newSearch = tree.getItem(NEW_SEARCH);
        tree.addItem(searchFilter);
        tree.setParent(searchFilter, newSearch);
        tree.setChildrenAllowed(searchFilter, false);
        tree.expandItem(NEW_SEARCH);
        tree.setValue(searchFilter);
    }

    public void setSelectedView(Class<? extends View> viewClass) {
        tree.removeValueChangeListener(listener);
        if (SearchView.class.isAssignableFrom(viewClass)) {
            setValue(NEW_SEARCH);
        } else if (ListView.class.isAssignableFrom(viewClass)) {
            setValue(SHOW_ALL);
        }
        tree.addValueChangeListener(listener);
    }

    private void setValue(String value) {
        tree.setValue(value);
    }
}
