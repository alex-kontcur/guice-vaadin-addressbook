package ru.bona.guice.vaadin.mvp.addressbook.ui.list;

import com.google.code.vaadin.components.preconfigured.Preconfigured;
import com.google.code.vaadin.mvp.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.CancelEditEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.EditPersonEvent;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.SavePersonEvent;
import ru.bona.guice.vaadin.mvp.addressbook.util.AddressBookStringToIntConverter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * PersonForm
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
public class PersonForm extends ViewComponent {

    private static final long serialVersionUID = -3172064774384767041L;

    /*
         * With @Preconfigured annotation you can define Vaadin component attributes
         * (immediateness, height, caption and so on..) that are applied
         * injection-time. captionKey = "save", states that the caption of the
         * injected button should be the text received from
         * TextBundle-implementation (Lang in the case of this project, the texts
         * are in AddressBookLang_en_US.properties) with the key "save".
         */
    @Inject
    @Preconfigured(captionKey = "save")
    private Button saveButton;

    @Inject
    @Preconfigured(captionKey = "cancel")
    private Button cancelButton;

    @Inject
    @Preconfigured(captionKey = "edit")
    private Button editButton;

    @Inject
    @Preconfigured
    private Form form;

    private Collection<String> cityOptions;

	/*===========================================[ CLASS METHODS ]================*/

    @Override
    protected void localize() {
        editButton.setCaption(getText("edit"));
        saveButton.setCaption(getText("save"));
        cancelButton.setCaption(getText("cancel"));
        initFieldFactory();
        PersonDto.Fields[] fieldNames = PersonDto.Fields.values();
        for (PersonDto.Fields fieldName : fieldNames) {
            Field field = form.getField(fieldName.name());
            if (field == null) {
                continue;
            }
            field.setCaption(getText("person-" + fieldName.name()));
        }
    }

    private void initFieldFactory() {
        form.setFormFieldFactory(new DefaultFieldFactory() {
            @Override
            public Field createField(Item item, Object propertyId, Component uiContext) {
                Field field = new TextField();

                if (propertyId.equals(PersonDto.Fields.city.name())) {
                    field = new ComboBox(getText("person-city"), cityOptions);
                } else if (propertyId.equals(PersonDto.Fields.postalCode.name())) {
                    field.setRequired(true);
                    ((TextField) field).setConverter(new AddressBookStringToIntConverter());
                } else if (propertyId.equals(PersonDto.Fields.email.name())) {
                    field.addValidator(new EmailValidator(getText("personform-error-email")));
                    field.setRequired(true);
                } else {
                    field = super.createField(item, propertyId, uiContext);
                }

                if (field instanceof AbstractTextField) {
                    ((AbstractTextField) field).setNullRepresentation("");
                }
                field.setWidth(200.0f, Unit.PIXELS);
                field.setCaption(getText("person-" + propertyId));
                return field;
            }
        });
    }

    public void editNewContact(PersonDto person) {
        init();
        setItemDataSource(new BeanItem<>(person));
        setReadOnly(false);
    }

    @Override
    protected void initComponent() {
        setCompositionRoot(form);
        initFieldFactory();
        constructFooter();
    }

    private void constructFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.setVisible(false);

        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (form.isValid()) {
                    form.commit();
                    PersonDto personDto = getItemPerson();
                    fireViewEvent(new SavePersonEvent(personDto));
                }
            }
        });
        footer.addComponent(saveButton);

        cancelButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                fireViewEvent(new CancelEditEvent());
            }
        });
        footer.addComponent(cancelButton);

        editButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                /*
                 * Clicking the edit button only fires an event and nothing
				 * more. The presenter which observes the event will decide what
				 * happens next.
				 */
                fireViewEvent(new EditPersonEvent());
            }
        });
        footer.addComponent(editButton);

        form.setFooter(footer);
    }

    @SuppressWarnings("unchecked")
    private PersonDto getItemPerson() {
        return ((BeanItem<PersonDto>) form.getItemDataSource()).getBean();
    }

    public void setItemDataSource(Item newDataSource) {
        if (newDataSource != null) {
            form.setItemDataSource(newDataSource, Arrays.asList(PersonList.NATURAL_COL_ORDER));
        }
        form.getFooter().setVisible(newDataSource != null);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        saveButton.setVisible(!readOnly);
        cancelButton.setVisible(!readOnly);
        editButton.setVisible(readOnly);
        form.setReadOnly(readOnly);
    }

    public void cancel() {
        if (getItemPerson().isPersistent()) {
            form.discard();
        } else {
            form.setItemDataSource(null);
        }
        setReadOnly(true);
    }

	/*===========================================[ GETTER/SETTER ]================*/

    public void setCityOptions(Collection<String> cityOptions) {
        this.cityOptions = new ArrayList<>(cityOptions);
    }
}