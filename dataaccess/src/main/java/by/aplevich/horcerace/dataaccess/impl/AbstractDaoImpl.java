package by.aplevich.horcerace.dataaccess.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.Validate;

import by.aplevich.horcerace.dataaccess.AbstractDao;

public abstract class AbstractDaoImpl<ID, Entity> implements AbstractDao<ID, Entity> {

    private final Class<Entity> entityClass;
    private final Class<ID> primaryKeyClass;
    private EntityManager em;

    protected AbstractDaoImpl(final Class<Entity> entityClass, final Class<ID> primaryKeyClass) {
        Validate.notNull(entityClass, "entityClass could not be a null");
        Validate.notNull(primaryKeyClass, "primaryKeyClass could not be a null");
        this.entityClass = entityClass;
        this.primaryKeyClass = primaryKeyClass;
    }

    @Override
    public Entity getById(ID id) {
        return em.find(getEntityClass(), id);
    }

    @PersistenceContext
    protected void setEntityManager(final EntityManager em) {
        this.em = em;
    }

    private Class<Entity> getEntityClass() {
        return entityClass;
    }

    private Class<ID> getPrimaryKeyClass() {
        return primaryKeyClass;
    }

}
