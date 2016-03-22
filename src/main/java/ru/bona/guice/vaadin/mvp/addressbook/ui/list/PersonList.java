package ru.bona.guice.vaadin.mvp.addressbook.ui.list;

import com.google.code.vaadin.components.preconfigured.Preconfigured;
import com.google.code.vaadin.mvp.ViewComponent;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.PersonDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.context.dto.SearchDto;
import ru.bona.guice.vaadin.mvp.addressbook.ui.event.PersonSelectedEvent;

import javax.inject.Inject;
import java.util.Collection;

import static com.vaadin.data.Property.ValueChangeEvent;

/**
 * PersonList
 *
 * @author Kontsur Alex
 * @since 24.01.13
 */
public class PersonList extends ViewComponent {

    private static final long serialVersionUID = -5323643662095754284L;

    @SuppressWarnings("PublicStaticArrayField")
    public static final Object[] NATURAL_COL_ORDER = {
        PersonDto.Fields.firstName.name(), PersonDto.Fields.lastName.name(), PersonDto.Fields.email.name(),
        PersonDto.Fields.phoneNumber.name(), PersonDto.Fields.streetAddress.name(),
        PersonDto.Fields.postalCode.name(), PersonDto.Fields.city.name()
    };

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    @Preconfigured(nullSelectionAllowed = false, sizeFull = true, immediate = true)
    private Table table;

	/*===========================================[ CLASS METHODS ]================*/

    @Override
    public void initComponent() {
        setCompositionRoot(table);
        table.setColumnCollapsingAllowed(true);
        table.setColumnReorderingAllowed(true);
        table.setSelectable(true);

        table.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 2514397406689185848L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                PersonDto person = (PersonDto) event.getProperty().getValue();
                if (person != null) {
                    fireViewEvent(new PersonSelectedEvent(person));
                }
            }
        });
        initColumns();
    }

    private void initColumns() {
        table.setContainerDataSource(new BeanItemContainer<>(PersonDto.class));
        table.setVisibleColumns(NATURAL_COL_ORDER);
        for (Object propertyId : table.getVisibleColumns()) {
            String header = getText("person-" + propertyId);
            table.setColumnHeader(propertyId, header);
        }

        table.addGeneratedColumn(PersonDto.Fields.email.name(), new ColumnGenerator() {
            private static final long serialVersionUID = -5912343061890634552L;

            @Override
            public Component generateCell(Table source, Object itemId, Object columnId) {
                String email = ((PersonDto) itemId).getEmail();
                return new Link(email, new ExternalResource("mailto:" + email));
            }
        });

        table.setSortContainerPropertyId(PersonDto.Fields.firstName.name());
    }

    @Override
    protected void localize() {
        table.setVisibleColumns(NATURAL_COL_ORDER);
        for (Object propertyId : table.getVisibleColumns()) {
            String header = getText("person-" + propertyId);
            table.setColumnHeader(propertyId, header);
        }
    }

    public void setPersonList(Collection<PersonDto> people) {
        table.removeAllItems();
        for (PersonDto person : people) {
            table.addItem(person);
        }
        table.sort();
        table.refreshRowCache();
    }

    public void addContactToList(PersonDto person) {
        table.addItem(person);
        table.sort();
    }

    public void applyFilter(SearchDto searchDto) {
        if (table == null) {
            return;
        }
        Container containerDataSource = table.getContainerDataSource();
        if (containerDataSource instanceof IndexedContainer) {
            return;
        }
        BeanItemContainer<PersonDto> container = (BeanItemContainer<PersonDto>) containerDataSource;
        // clear previous filters
        container.removeAllContainerFilters();

        if (searchDto != null) {
            // filter contacts with given filter
            container.addContainerFilter(searchDto.getProperty(), searchDto.getTerm(), true, false);

            String propertyName = getText("person-" + searchDto.getProperty());
            /*
			 * personlist-searchnotification -text has 3 parameters which are
			 * passed in the Lang.getText-method.
			 */
            String notificationText = getText("personlist-searchnotification", propertyName, searchDto.getTerm(), container.size());
            Notification.show(notificationText, Notification.Type.TRAY_NOTIFICATION);
        }
    }

    public void setValue(PersonDto person) {
        table.setValue(person);
    }

    public Item getSelectedItem() {
        return table.getItem(table.getValue());
    }
}