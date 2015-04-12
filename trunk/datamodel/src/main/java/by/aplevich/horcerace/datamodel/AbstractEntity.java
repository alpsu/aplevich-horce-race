package by.aplevich.horcerace.datamodel;

import javax.persistence.MappedSuperclass;

/**
 * Common fields in all datamodel classe
 */

@MappedSuperclass
public abstract class AbstractEntity {
    public abstract Long getId();

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;

        return !(getId() != null ? !getId().equals(that.getId()) : that.getId() != null);
    }
}