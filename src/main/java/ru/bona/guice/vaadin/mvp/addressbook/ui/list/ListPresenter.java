package ru.bona.guice.vaadin.mvp.addressbook.ui.list;

import com.google.code.guice.repository.configuration.PersistenceUnitsConfigurationManager;
import com.google.code.vaadin.mvp.AbstractPresenter;
import com.google.code.vaadin.mvp.eventhandling.Observes;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import ru.bona.guice.vaadin.mvp.addressbook.model.command.person.CreatePersonCommand;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;
import ru.bona.guice.vaadin.mvp.addressbook.model.service.PersonDiffService;
import ru.bona.guice.vaadin.mvp.addressbook.query.PersonEntry;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.CityRepository;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.PersonRepository;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.CityMapper;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.PersonMapper;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * List presenter is the presenter of ListView. EJBs and other resources should
 * mainly be accessed in the presenter and results are pushed to the view
 * implementation trough it's API.
 * 
 * If implemented correctly, the presenter imports shouldn't include any
 * com.vaadin... packages (presenter and view implementation are not dependent).
 * Nicest approach would be to have all the presenters, view 
 * implementations(and subcomponents) and interfaces in separate modules.
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
@SuppressWarnings("CdiInjectionPointsInspection")
public class ListPresenter extends AbstractPresenter<ListView> {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 3142005604838420287L;

    // CDI Utils includes a built-in CDI event qualifier @EventQualifier which
    // uses a String (method identifier) and a View interface as it's members.
    // All the method id's specific to List-view are located here. Of course you
    // can define your own qualifiers as well but the main idea is to observe
    // events in presenters and fireSavePerson the events in view-implementations and
    // subcomponents.
    public static final String PERSON_SELECTED = "person_selected";
    public static final String EDIT_PERSON = "edit_person";
    public static final String SAVE_PERSON = "save_person";
    public static final String CANCEL_EDIT = "cancel_edit";

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    private PersonEntry currentPerson;


    @Inject
    private EventBus eventBus;

    @Inject
    private CityMapper cityMapper;

    @Inject
    private PersonMapper personMapper;

    @Inject
    private CommandGateway commandGateway;

    @Inject
    private CityRepository cityRepository;

    @Inject
    private PersonRepository personRepository;

    @Inject
    private PersonDiffService personDiffService;

    /*===========================================[ CLASS METHODS ]================*/

    /*
     * This method observes events with a ParameterDTO as parameter and an
     *
     * @EventQualifier as qualifier. The method is invoked if the qualifiers
     * methodId = PERSON_SELECTED and viewInterface = ListView.class.
     * ListView.class is used because only the presenter of ListView is supposed
     * to receive an event. If other presenters should also observe an event
     * (fired from ListViewImpl for example), View.class would be used as the
     * viewInterface member instead.
     */
    @Observes
    protected void personSelected(PersonSelectedEvent personSelectedEvent) {
        PersonDto personDto = personSelectedEvent.getPersonDto();
        currentPerson = personRepository.findOne(personDto.getId());
        view.showSelectedPersonDetails();
    }

    @Observes
    protected void editPerson(EditPersonEvent editPersonEvent) {
        view.editSelectedPerson();
    }

    /*
     * This method nicely demonstrates how the control logic over views can be
     * handled in presenters: If the person isn't persistent yet, the view is
     * told to add the newly persisted person to the navigation tree. Otherwise
     * the view is only told to set the person selected.
     */
    @Observes
    protected void savePerson(SavePersonEvent savePersonEvent) {
        PersonDto personDto = savePersonEvent.getPersonDto();
        if (currentPerson == null) {
            //new Person
            if (personDto.getId() == null) {
                CityId cityId = cityRepository.findByName(personDto.getCity()).getId();
                commandGateway.send(new CreatePersonCommand(
                        new PersonId(),
                        personDto.getFirstName(),
                        personDto.getLastName(),
                        personDto.getEmail(),
                        personDto.getPhoneNumber(),
                        personDto.getStreetAddress(),
                        personDto.getPostalCode(),
                        cityId));

                view.setPersonList(personMapper.buildPersonDtoList(personRepository.findAll()));
            }
        } else {
            PersonId personId = currentPerson.getPersonId();
            if (personId.equals(personDto.getId())) {
                List<Object> diffCommands = personDiffService.getDiffCommands(personId, currentPerson, personDto);
                for (Object command : diffCommands) {
                    commandGateway.send(command);
                }
            } else {
                throw new IllegalStateException("current person in Presenter doesn't equals to person from View");
            }
        }

        view.selectPerson(personDto);
    }

    @Observes
    protected void cancel(CancelEditEvent cancelEditEvent) {
        logger.info("cancelEditing()");
        view.cancelEditing();
    }

    @Observes
    protected void showAll(ShowAllEvent showAllEvent) {
        view.setPersonList(personMapper.buildPersonDtoList(personRepository.findAll()));
        view.applyFilter(null);
    }

    /*
     * The @EventQualifier of this method has viewInterface = View.class as it's
     * member meaning that some other presenter (MainPresenter) is also
     * observing the same event. MainPresenter does whatever it does (sets
     * ListView as currently visible view) but here ListView is told to edit a
     * newly instantiated contact (Person).
     */
    @Observes
    protected void newContact(NewContactEvent newContactEvent) {
        currentPerson = null;
        view.editNewContact(new PersonDto());
    }

    @Observes
    protected void search(SearchEvent searchEvent) {
        SearchDto searchFilter = searchEvent.getSearchDto();
        view.applyFilter(searchFilter);
    }

    /*===========================================[ INTERFACE METHODS ]============*/

    @Inject
    private EntityManager entityManager;

    @Inject
    private PersistenceUnitsConfigurationManager pucm;
    /*
     * When the ListPresenter is initialized, it fetches a list of cities from
     * backend (personDAO) and passes the list to the view.
     */
    @Override
    protected void initPresenter() {
        view.setCityOptions(cityMapper.buildCityDtoList(cityRepository.findAll()));
    }

    @Override
    public void viewOpened() {
        logger.info("opened");
    }
}
