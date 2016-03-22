package ru.bona.guice.vaadin.mvp.addressbook;

import com.google.code.axonguice.AxonGuiceModule;
import com.google.code.guice.repository.configuration.JpaRepositoryModule;
import com.google.code.guice.repository.configuration.RepositoryBinder;
import com.google.code.vaadin.application.AbstractMVPApplicationModule;
import com.google.code.vaadin.localization.TextBundle;
import com.google.inject.servlet.ServletScopes;
import ru.bona.guice.vaadin.mvp.addressbook.model.service.BootstrapService;
import ru.bona.guice.vaadin.mvp.addressbook.model.service.PersonDiffService;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.CityRepository;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.PersonRepository;
import ru.bona.guice.vaadin.mvp.addressbook.query.repository.SearchRepository;
import ru.bona.guice.vaadin.mvp.addressbook.ui.localization.Lang;

import javax.servlet.ServletContext;

/**
 * AddressBookModule - guice module for address book app
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
public class AddressBookModule extends AbstractMVPApplicationModule {

    /*===========================================[ CONSTRUCTORS ]=================*/

    public AddressBookModule(ServletContext servletContext) {
        super(servletContext);
    }

    /*===========================================[ INTERFACE METHODS ]============*/

    @Override
    protected void installModules() {
        install(new JpaRepositoryModule("persons.h2", "persons.h2.secondary") {
            @Override
            protected void bindRepositories(RepositoryBinder binder) {
                binder.bind(SearchRepository.class).to("persons.h2");
                binder.bind(PersonRepository.class).to("persons.h2");
                binder.bind(CityRepository.class).to("persons.h2");
            }
        });

        install(new AxonGuiceModule("ru.bona"){
            @Override
            protected boolean isJsr250SupportEnabled() {
                return false;
            }
        });
        logger.info("Security system activated");
    }

    @Override
    protected void bindTextBundle() {
        bind(TextBundle.class).to(Lang.class).in(ServletScopes.SESSION);
    }

    @Override
    protected void bindComponents() {
        //bindConstant().annotatedWith(Names.named("shiro.redirectUrl")).to("/login.jsp");
        //addFilterChain("/logout", LOGOUT);

        bind(BootstrapService.class).asEagerSingleton();
        bind(PersonDiffService.class).asEagerSingleton();
    }

}
