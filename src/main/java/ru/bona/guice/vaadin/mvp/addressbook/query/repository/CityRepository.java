package ru.bona.guice.vaadin.mvp.addressbook.query.repository;

import com.google.code.guice.repository.BatchStoreJpaRepository;
import com.google.code.guice.repository.EntityManagerProvider;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.CityId;
import ru.bona.guice.vaadin.mvp.addressbook.query.CityEntry;

/**
 * CityRepository
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
public interface CityRepository extends BatchStoreJpaRepository<CityEntry, CityId>, EntityManagerProvider {
    CityEntry findByName(String name);
}
