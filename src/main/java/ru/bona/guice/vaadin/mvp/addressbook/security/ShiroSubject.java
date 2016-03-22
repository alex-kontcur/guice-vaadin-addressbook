package ru.bona.guice.vaadin.mvp.addressbook.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;

import javax.inject.Inject;

/**
 * ShiroSubject
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
public class ShiroSubject implements Subject {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private SecurityManager securityManager;

	/*===========================================[ INTERFACE METHODS ]============*/

    @Override
    public boolean hasPermission(Permission permission) {
        return securityManager.hasRole(SecurityUtils.getSubject().getPrincipals(), permission.toString());
    }
}
