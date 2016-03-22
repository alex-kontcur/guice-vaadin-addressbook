package ru.bona.guice.vaadin.mvp.addressbook.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;
import ru.bona.guice.vaadin.mvp.addressbook.query.PersonEntry;

/**
 * PersonRepository
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
public interface PersonRepository extends JpaRepository<PersonEntry, PersonId> {
}
