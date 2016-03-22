package ru.bona.guice.vaadin.mvp.addressbook.ui.login;

import com.google.code.vaadin.mvp.AbstractView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import ru.bona.guice.vaadin.mvp.addressbook.security.ApplicationSecurity;
import ru.bona.guice.vaadin.mvp.addressbook.ui.main.MainView;

/**
 * LoginViewImpl
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
public class LoginViewImpl extends AbstractView implements LoginView, View {

    private static final long serialVersionUID = 3799423608097000060L;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        logger.info("enter, event = " + event.toString());
    }

    @Override
    protected void initView() {
        setCaption("Login");

        final TextField username = new TextField("Username");
        final PasswordField password = new PasswordField("Password");
        final CheckBox rememberMe = new CheckBox("Remember Me");

        VerticalLayout layout = new VerticalLayout(username, password, rememberMe);

        username.focus();

        username.setValue("admin");
        password.setValue("admin");

        if (ApplicationSecurity.isRemembered()) {
            username.setValue(ApplicationSecurity.whoIsRemembered());
            rememberMe.setValue(ApplicationSecurity.isRemembered());
            password.focus();
        }

        @SuppressWarnings("serial") Button login = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Navigator navigator = UI.getCurrent().getNavigator();
                boolean loggedIn = ApplicationSecurity.login(username.getValue(), password.getValue(), rememberMe.getValue());
                navigator.navigateTo(loggedIn ? MainView.resourceName : LoginView.resourceName);
            }
        });

        layout.addComponent(login);
        setCompositionRoot(layout);
    }
}
