package ru.bona.guice.vaadin.mvp.addressbook.security;

import com.google.inject.Provider;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * SubjectProvider
 *
 * @author Kontsur Alex (bona)
 * @since 02.02.13
 */
public class SubjectProvider implements Provider<Subject> {

	/*===========================================[ INTERFACE METHODS ]============*/

    @Override
    public Subject get() {
        return SecurityUtils.getSubject();
    }
}
