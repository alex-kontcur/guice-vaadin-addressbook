package ru.bona.guice.vaadin.mvp.addressbook.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bona.guice.vaadin.mvp.addressbook.model.command.person.*;
import ru.bona.guice.vaadin.mvp.addressbook.model.identity.PersonId;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PersonDiffService
 *
 * @author Kontsur Alex (bona)
 * @since 26.01.13
 */
@Singleton
public class PersonDiffService {

    private static final String FIRST_NAME_FIELD = "firstName";
    private static final String LAST_NAME_FIELD = "lastName";
    private static final String EMAIL_FIELD = "email";
    private static final String PHONE_NUMBER_FIELD = "phoneNumber";
    private static final String STREET_ADDRESS_FIELD = "streetAddress";
    private static final String POSTAL_CODE_FIELD = "postalCode";


    private static final Map<String, Class> commands = new ConcurrentHashMap<>();
    static {
        commands.put(FIRST_NAME_FIELD, ChangePersonFirstNameCommand.class);
        commands.put(LAST_NAME_FIELD, ChangePersonLastNameCommand.class);
        commands.put(EMAIL_FIELD, ChangePersonEmailCommand.class);
        commands.put(PHONE_NUMBER_FIELD, ChangePersonPhoneNumberCommand.class);
        commands.put(STREET_ADDRESS_FIELD, ChangePersonStreetAddressCommand.class);
        commands.put(POSTAL_CODE_FIELD, ChangePersonPostalCodeCommand.class);
    }

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private static final Logger logger = LoggerFactory.getLogger(PersonDiffService.class);

	/*===========================================[ CLASS METHODS ]================*/

    public Map<String, Object> getDiffMap(Object entry, Object dto) {
        Map<String, Object> out = new HashMap<>();
        Field[] fields = entry.getClass().getFields();
        for (Field field : fields) {
            try {
                Field fieldDto = PersonDto.class.getField(field.getName());
                Object fieldDtoValue = fieldDto.get(dto);
                if (!field.get(entry).equals(fieldDtoValue)) {
                    out.put(field.getName(), fieldDtoValue);
                }
            } catch (Exception ignored) {
            }
        }
        return out;
    }

    public List<Object> getDiffCommands(Object id, Object entry, Object dto) {
        List<Object> out = new ArrayList<>();
        Map<String, Object> diffMap = getDiffMap(entry, dto);
        for (Map.Entry<String, Object> mapEntry : diffMap.entrySet()) {
            Class clazz = commands.get(mapEntry.getKey());
            try {
                Constructor constructor = clazz.getConstructor(PersonId.class, Object.class);
                out.add(constructor.newInstance(id, mapEntry.getValue()));
            } catch (Exception e) {
                logger.error(String.format("Error while creating command for %s", mapEntry.getKey()), e);
            }
        }
        return out;
    }

}