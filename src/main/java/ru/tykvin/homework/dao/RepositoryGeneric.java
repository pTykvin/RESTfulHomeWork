package ru.tykvin.homework.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class RepositoryGeneric<T> implements IRepositoryGeneric<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> type;

    public RepositoryGeneric() {

    }

    public RepositoryGeneric(Class<T> type) {
        this.type = type;
    }

    @Override
    public T save(T emp) {
        entityManager.persist(emp);
        entityManager.flush();
        return emp;
    }

    @Override
    public void delete(T emp) {
        entityManager.remove(emp);
    }

    @Override
    public void delete(long id) {
        delete(find(id));
    }

    @Override
    public T edit(T emp) {
        return entityManager.merge(emp);
    }

    @Override
    public T find(Long empId) {
        return entityManager.find(type, empId);
    }

    @Override
    public Collection<T> findAll() {
        return entityManager.createQuery("from " + type.getSimpleName(), type).getResultList();
    }

}
