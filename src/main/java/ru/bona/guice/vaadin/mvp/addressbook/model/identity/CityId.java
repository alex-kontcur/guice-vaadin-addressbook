package ru.bona.guice.vaadin.mvp.addressbook.model.identity;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * CityId
 *
 * @author Kontsur Alex (bona)
 * @since 27.02.13
 */
@Embeddable
public class CityId implements Serializable {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = 1963700038359841075L;

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @Column(name="ID")
    private final String identifier;

    private final int hashCode;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public CityId() {
        identifier = IdentifierFactory.getInstance().generateIdentifier();
        hashCode = identifier.hashCode();
    }

    public CityId(String identifier) {
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
        CityId companyId = (CityId) obj;
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
