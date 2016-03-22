package ru.bona.guice.vaadin.mvp.addressbook.query.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bona.guice.vaadin.mvp.addressbook.model.Person;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.SearchId;
import ru.bona.guice.vaadin.mvp.addressbook.query.SearchEntry;

/**
 * SearchRepository
 *
 * @author Kontsur Alex
 * @since 29.01.13
 */
public interface SearchRepository extends JpaRepository<SearchEntry, SearchId> {
    @Query("select s from SearchEntry s where s.term = :term")
    Person findSearchByTerm(@Param("term") String term);
}
