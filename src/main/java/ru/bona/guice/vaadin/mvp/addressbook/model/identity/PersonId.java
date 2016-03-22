package ru.bona.guice.vaadin.mvp.addressbook.model.identity;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * PersonId
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@Embeddable
public class PersonId implements Serializable {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -823709421615631043L;

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    private final String identifier;
    private final int hashCode;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public PersonId() {
        identifier = IdentifierFactory.getInstance().generateIdentifier();
        hashCode = identifier.hashCode();
    }

    public PersonId(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.identifier = identifier;
        hashCode = identifier.hashCode();
    }

	/*===========================================[ CLASS METHODS ]================*/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PersonId companyId = (PersonId) obj;
        return identifier.equals(companyId.identifier);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
