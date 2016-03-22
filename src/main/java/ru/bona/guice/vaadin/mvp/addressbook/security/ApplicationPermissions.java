package ru.bona.guice.vaadin.mvp.addressbook.security;

/**
 * ApplicationPermissions
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
public interface ApplicationPermissions {

    /*===========================================[ INTERFACE METHODS ]==============*/

    Permission CONTACTS_CREATE = Permission.valueOf("contacts:create");
    Permission CONTACTS_READ = Permission.valueOf("contacts:read");
    Permission CONTACTS_UPDATE = Permission.valueOf("contacts:update");
    Permission CONTACTS_DROP = Permission.valueOf("contacts:drop");
}
