package ru.bona.guice.vaadin.mvp.addressbook.ui.main;

import com.google.code.vaadin.application.uiscope.UIScoped;
import com.google.code.vaadin.mvp.AbstractView;
import com.google.code.vaadin.mvp.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.ShowAllEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.list.ListView;
import ru.bona.guice.vaadin.mvp.addressbook.ui.search.SearchView;

import javax.inject.Inject;

/**
 * MainViewImpl
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@UIScoped
public class MainViewImpl extends AbstractView implements MainView, com.vaadin.navigator.View {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -5710417349077870600L;

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private NavigationTree tree;
    @Inject
    private Toolbar toolbar;
    @Inject
    private HorizontalSplitPanel horizontalSplit;

    @Inject
    private ListView listView;
    @Inject
    private SearchView searchView;

    // Windows
    @Inject
    private HelpWindow helpWindow;
    @Inject
    private SharingOptions sharingOptions;

	/*===========================================[ CLASS METHODS ]================*/

    @Override
    protected void localize() {
        helpWindow.updateText();
        sharingOptions.updateText();
    }

	/*===========================================[ INTERFACE METHODS ]============*/

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        logger.info("enter, event = " + event.toString());
    }

    @Override
    public void setView(Class<? extends View> viewClass, boolean selectInNavigationTree) {
        View view = null;
        if (SearchView.class.isAssignableFrom(viewClass)) {
            view = searchView;
        } else if (ListView.class.isAssignableFrom(viewClass)) {
            view = listView;
        }
        horizontalSplit.setSecondComponent(view);
        if (selectInNavigationTree) {
            tree.setSelectedView(viewClass);
        }

        if (view != null) {
            view.open();
        }
    }

    @Override
    public void showHelpWindow() {
        if (helpWindow.getParent() == null) {
            UI.getCurrent().addWindow(helpWindow);
        }
    }

    @Override
    public void showShareWindow() {
        if (sharingOptions.getParent() == null) {
            UI.getCurrent().addWindow(sharingOptions);
        }
    }

    @Override
    public void addSearchToTree(SearchDto searchFilter) {
        tree.addSearchToTree(searchFilter);
    }

    @Override
    protected void initView() {
        setSizeFull();

        VerticalLayout mainLayout = new VerticalLayout();
        setCompositionRoot(mainLayout);
        mainLayout.setSizeFull();

        toolbar.init();
        mainLayout.addComponent(toolbar);

        mainLayout.addComponent(horizontalSplit);
        mainLayout.setExpandRatio(horizontalSplit, 1);

        tree.init();
        horizontalSplit.setFirstComponent(tree);
        horizontalSplit.setSplitPosition(200, Unit.PIXELS);

        helpWindow.init();
        sharingOptions.init();

        // Initialization of the ListView
        setView(ListView.class, true);
        fireViewEvent(new ShowAllEvent());
    }
}
