package ru.tsystems.school.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }


    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findById(int id) {
        return entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("select e from " + clazz.getName() + " e")
                .getResultList();
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }


    public void delete(T entity) {
        entityManager.remove(entity);
    }


    public void deleteById(int entityId) {
        T entity = findById(entityId);
        delete(entity);
    }
}
