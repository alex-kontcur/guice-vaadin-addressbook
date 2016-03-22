package ru.bona.guice.vaadin.mvp.addressbook.security;

/**
 * Subject
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
public interface Subject {

	/*===========================================[ CLASS METHODS ]================*/

    boolean hasPermission(Permission permission);
}
