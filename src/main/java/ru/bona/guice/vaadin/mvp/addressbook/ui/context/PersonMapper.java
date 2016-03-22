package ru.bona.guice.vaadin.mvp.addressbook.ui.context;

import ru.bona.guice.vaadin.mvp.addressbook.query.PersonEntry;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * PersonMapper - maps model Person to DTO Person and and forth
 *
 * @author Kontsur Alex
 * @since 30.01.13
 */
@Singleton
public class PersonMapper {

	/*===========================================[ CLASS METHODS ]================*/

    public List<PersonDto> buildPersonDtoList(List<PersonEntry> personEntries) {
        List<PersonDto> out = new ArrayList<>();
        for (PersonEntry personEntry : personEntries) {
            out.add(buildPersonDto(personEntry));
        }
        return out;
    }

    public PersonDto buildPersonDto(PersonEntry personEntry) {
        PersonDto personDto = new PersonDto();
        personDto.setId(personEntry.getPersonId());
        personDto.setFirstName(personEntry.getFirstName());
        personDto.setLastName(personEntry.getLastName());
        personDto.setEmail(personEntry.getEmail());
        personDto.setPhoneNumber(personEntry.getPhoneNumber());
        personDto.setStreetAddress(personEntry.getStreetAddress());
        personDto.setPostalCode(personEntry.getPostalCode());
        personDto.setCity(personEntry.getCity().getName());
        return personDto;
    }
}