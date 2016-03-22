package ru.bona.guice.vaadin.mvp.addressbook.security;

import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import org.apache.shiro.config.Ini;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 * AddressbookShiroModule
 *
 * @author Kontsur Alex (bona)
 * @since 02.02.13
 */
public class AddressbookShiroModule extends ShiroWebModule {

    /*===========================================[ CONSTRUCTORS ]=================*/

    public AddressbookShiroModule(ServletContext servletContext) {
        super(servletContext);
    }

    /*===========================================[ CLASS METHODS ]================*/

    @Provides
    Ini loadShiroIni() {
        return Ini.fromResourcePath("classpath:web-security.ini");
    }

    /*===========================================[ INTERFACE METHODS ]============*/

    @Override
    protected void configureShiroWeb() {
        bindConstant().annotatedWith(Names.named("shiro.loginUrl")).to("/login.jsp");
        bindConstant().annotatedWith(Names.named("shiro.successUrl")).to("/");
        //bind(CachingSecurityManager.class).to(DefaultSecurityManager.class).in(Singleton.class);


        try {
            bindRealm().toConstructor(IniRealm.class.getConstructor(Ini.class));
        } catch (NoSuchMethodException e) {
            addError(e);
        }

        bindConstant().annotatedWith(Names.named("myRedirectUrl")).to("/login.jsp");
        Key<MyLogoutFilter> MYLOGOUT = Key.get(MyLogoutFilter.class);
        addFilterChain("/logout", MYLOGOUT);

        addFilterChain("/login.jsp", AUTHC);
        //addFilterChain("/logout", LOGOUT);
        addFilterChain("/VAADIN/themes/**", ANON);
        addFilterChain("/**", AUTHC_BASIC);

/*
        bindConstant().annotatedWith(Names.named("shiro.redirectUrl")).to("/login.jsp");
        addFilterChain("/logout", LOGOUT);

        //bindConstant().annotatedWith(Names.named("myRedirectUrl")).to("/login.jsp");
        //Key<MyLogoutFilter> MYLOGOUT = Key.get(MyLogoutFilter.class);
        //addFilterChain("/logout", MYLOGOUT);
*/
        bind(Subject.class).to(ShiroSubject.class);
        expose(Subject.class);
    }

    public class MyLogoutFilter extends LogoutFilter {
        @Inject
        @Override
        public void setRedirectUrl(@Named("myRedirectUrl") String redirectUrl) {
            super.setRedirectUrl(redirectUrl);
        }
    }
}
