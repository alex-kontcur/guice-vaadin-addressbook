package ru.bona.guice.vaadin.mvp.addressbook.security;

/**
 * Permission
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
public class Permission {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private String name;

	/*===========================================[ CONSTRUCTORS ]=================*/

    protected Permission(String name) {
        this.name = name;
    }

	/*===========================================[ CLASS METHODS ]================*/

    public static Permission valueOf(String permissionName) {
        return new Permission(permissionName);
    }

    @Override
    public String toString() {
        return name;
    }
}
