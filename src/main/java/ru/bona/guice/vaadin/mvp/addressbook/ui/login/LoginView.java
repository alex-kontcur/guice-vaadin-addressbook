package ru.bona.guice.vaadin.mvp.addressbook.ui.login;

import com.google.code.vaadin.mvp.View;
import com.google.inject.ImplementedBy;

/**
 * LoginView
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@ImplementedBy(LoginViewImpl.class)
public interface  LoginView extends View {

    String resourceName = "/addressbook";

}
