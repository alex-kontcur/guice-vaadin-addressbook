package ru.bona.guice.vaadin.mvp.addressbook.model.service;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bona.guice.vaadin.mvp.addressbook.model.command.CreateCityCommand;
import ru.bona.guice.vaadin.mvp.addressbook.model.command.person.CreatePersonCommand;
import ru.bona.guice.vaadin.mvp.addressbook.model.event.CityCreatedEvent;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.CityRepository;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.PersonRepository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.security.SecureRandom;
import java.util.Random;

/**
 * BootstrapService
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@SuppressWarnings("CdiInjectionPointsInspection")
public class BootstrapService {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final Logger logger = LoggerFactory.getLogger(BootstrapService.class);

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final String[] fnames = {"Peter", "Alice", "Joshua", "Mike", "Olivia",
            "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
            "Lisa", "Marge"};

    private static final String[] lnames = {"Smith", "Gordon", "Simpson", "Brown",
            "Clavel", "Simons", "Verne", "Scott", "Allison", "Gates",
            "Rowling", "Barks", "Ross", "Schneider", "Tate"};

    private static final String[] streets = {"4215 Blandit Av.", "452-8121 Sem Ave",
            "279-4475 Tellus Road", "4062 Libero. Av.", "7081 Pede. Ave",
            "6800 Aliquet St.", "P.O. Box 298, 9401 Mauris St.",
            "161-7279 Augue Ave", "P.O. Box 496, 1390 Sagittis. Rd.",
            "448-8295 Mi Avenue", "6419 Non Av.",
            "659-2538 Elementum Street", "2205 Quis St.",
            "252-5213 Tincidunt St.", "P.O. Box 175, 4049 Adipiscing Rd.",
            "3217 Nam Ave", "P.O. Box 859, 7661 Auctor St.",
            "2873 Nonummy Av.", "7342 Mi, Avenue",
            "539-3914 Dignissim. Rd.", "539-3675 Magna Avenue",
            "Ap #357-5640 Pharetra Avenue", "416-2983 Posuere Rd.",
            "141-1287 Adipiscing Avenue", "Ap #781-3145 Gravida St.",
            "6897 Suscipit Rd.", "8336 Purus Avenue", "2603 Bibendum. Av.",
            "2870 Vestibulum St.", "Ap #722 Aenean Avenue",
            "446-968 Augue Ave", "1141 Ultricies Street",
            "Ap #992-5769 Nunc Street", "6690 Porttitor Avenue",
            "Ap #105-1700 Risus Street",
            "P.O. Box 532, 3225 Lacus. Avenue", "736 Metus Street",
            "414-1417 Fringilla Street", "Ap #183-928 Scelerisque Road",
            "561-9262 Iaculis Avenue"};

    private static final String[] cities = {
            "Amsterdam", "Berlin", "Helsinki", "Hong Kong", "London", "Luxemburg",
            "New York", "Oslo", "Paris", "Rome", "Stockholm", "Tokyo", "Turku"
    };

    /*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private PersonRepository personRepository;

    @Inject
    private CityRepository cityRepository;

    @Inject
    private CommandGateway commandGateway;

    @Inject
    private EventBus eventBus;

    @PostConstruct
    void postConstuct() {
        final AnnotationEventListenerAdapter listener = AnnotationEventListenerAdapter.subscribe(this, eventBus);
      /*  UI.getCurrent().addDetachListener(new ClientConnector.DetachListener() {
            private static final long serialVersionUID = -5761980729944502101L;

            @Override
            public void detach(ClientConnector.DetachEvent event) {
                eventBus.unsubscribe(listener);
            }
        });*/

        createTestData();
    }

	/*===========================================[ CLASS METHODS ]================*/

    public void createTestData() {
        if (cityRepository.count() == 0) {
            for (String city : cities) {
                commandGateway.send(new CreateCityCommand(new CityId(), city));
            }
        }
    }
    /*===========================================[ CLASS METHODS ]================*/

    @EventHandler
    public void handle(CityCreatedEvent event) {
        Random random = new SecureRandom();
        for (int j = 0; j < 1; j++) {
            String fname = fnames[random.nextInt(fnames.length)];
            String lname = lnames[random.nextInt(lnames.length)];
            int postalCode = random.nextInt(100000);
            if (postalCode < 10000) {
                postalCode += 10000;
            }
            commandGateway.send(new CreatePersonCommand(new PersonId(),
                    fname,
                    lname,
                    fname.toLowerCase() + "." + lname.toLowerCase() + "@vaadin.com",
                    "+358 02 555 " + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10),
                    streets[random.nextInt(streets.length)],
                    postalCode,
                    event.getCityId()
            ));
        }
    }
}