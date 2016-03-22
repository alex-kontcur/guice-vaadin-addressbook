package ru.bona.guice.vaadin.mvp.addressbook.security;

import com.vaadin.ui.Notification;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * ApplicationSecurity
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
public class ApplicationSecurity {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSecurity.class);

	/*===========================================[ CONSTRUCTORS ]=================*/

    private ApplicationSecurity() {
    }

	/*===========================================[ CLASS METHODS ]================*/

    public static boolean login(String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        try {
            logger.info(String.format("Login requested for %s", subject.getPrincipal()));

            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                token.setRememberMe(rememberMe);
                subject.login(token);
            }

            logger.info(String.format("Login succeeded for %s", subject.getPrincipal()));
            return true;
        } catch (UnknownAccountException e) {
            logger.warn(String.format("Login failed for %s: %s", username, e.getMessage()));
            logger.debug(e.getMessage());
            Notification.show("Invalid Credentials", Notification.Type.WARNING_MESSAGE);
        } catch (IncorrectCredentialsException e) {
            logger.warn(String.format("Login failed for %s: %s", username, e.getMessage()));
            logger.debug(e.getMessage());
            Notification.show("Invalid Credentials", Notification.Type.WARNING_MESSAGE);
        } catch (LockedAccountException e) {
            logger.warn(String.format("Login failed for %s: %s", username, e.getMessage()));
            logger.debug(e.getMessage());
            Notification.show("Account is Locked", Notification.Type.WARNING_MESSAGE);
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            Notification.show("Unknown Security Error", Notification.Type.WARNING_MESSAGE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Notification.show("Unknown System Error", Notification.Type.WARNING_MESSAGE);
        }

        logger.info(String.format("Login failed for %s", username));
        return false;
    }

    public static void logout() {
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            subject.logout();
            logger.info(String.format("Logout succeeded for %s", subject.getPrincipal()));
        }
    }

    public static boolean isAuthenticated() {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated();
    }

    public static String getPrincipal() {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
    }

    public static boolean isRemembered() {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        return subject.isRemembered();
    }

    public static boolean isGuest() {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal() == null;
    }

    public static String whoIsRemembered() {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
    }

    public static boolean hasRole(String role) {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        return subject.hasRole(role);
    }

    public static boolean[] hasRoles(List<String> roles) {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        return subject.hasRoles(roles);
    }

    public static boolean hasAllRoles(List<String> roles) {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        return subject.hasAllRoles(roles);
    }

    public static boolean hasPermission(String permission) {

        Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(permission);
    }

    public static boolean[] hasPermission(List<String> permissions) {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();
        boolean[] permissionList = new boolean[permissions.size()];

        for (int i = 0; i < permissions.size(); i++) {
            permissionList[i] = subject.isPermitted(permissions.get(i));
        }

        return permissionList;
    }

    public static boolean hasAllPermissions(List<String> permissions) {
        //logger.executionTrace();

        Subject subject = SecurityUtils.getSubject();

        for (String permission : permissions) {
            if (!subject.isPermitted(permission)) {
                return false;
            }
        }

        return true;
    }
}
