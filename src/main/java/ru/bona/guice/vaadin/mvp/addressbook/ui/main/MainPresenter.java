package ru.bona.guice.vaadin.mvp.addressbook.ui.main;

import com.google.code.vaadin.mvp.AbstractPresenter;
import com.google.code.vaadin.mvp.eventhandling.Observes;
import org.axonframework.commandhandling.gateway.CommandGateway;
import ru.bona.guice.vaadin.mvp.addressbook.model.command.CreateSearchCommand;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.SearchId;
import ru.bona.guice.vaadin.mvp.addressbook.model.service.BootstrapService;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.*;
import ru.bona.guice.vaadin.mvp.addressbook.ui.list.ListView;
import ru.bona.guice.vaadin.mvp.addressbook.ui.search.SearchView;

import javax.inject.Inject;

/**
 * MainPresenter
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
public class MainPresenter extends AbstractPresenter<MainView> {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -1541169530735467514L;
    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private CommandGateway commandGateway;

    @Inject
    private BootstrapService bootstrapService;

    /*===========================================[ CLASS METHODS ]================*/

    @Observes
    protected void shareWindowRequested(ShareEvent shareEvent) {
        view.showShareWindow();
    }

    @Observes
    protected void helpWindowRequested(HelpEvent helpEvent) {
        view.showHelpWindow();
    }

    @Observes
    protected void showAll(ShowAllEvent showAllEvent) {
        view.setView(ListView.class, true);
    }

    @Observes
    protected void newSearch(NewSearchEvent newSearchEvent) {
        view.setView(SearchView.class, true);
    }

    @Observes
    protected void newContact(NewContactEvent newContactEvent) {
        view.setView(ListView.class, true);
    }

    @Observes
    protected void saveSearch(SaveSearchEvent saveSearchEvent) {
        SearchDto searchDto = saveSearchEvent.getSearchDto();
        commandGateway.send(new CreateSearchCommand(
            new SearchId(), searchDto.getSearchName(), searchDto.getProperty(), searchDto.getTerm()));

        view.addSearchToTree(searchDto);
    }

    @Observes
    protected void search(SearchEvent searchEvent) {
        view.setView(ListView.class, false);
    }

    /*===========================================[ INTERFACE METHODS ]============*/

    @Override
    protected void initPresenter() {
        logger.info("************************ initPresenter ************************ ");
    }

    @Override
    public void viewOpened() {

    }

}
