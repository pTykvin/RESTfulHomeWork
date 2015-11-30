package ru.tykvin.homework.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.tykvin.homework.exception.UnprocessableEntityException;

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
    public T save(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void delete(long id) {
        delete(find(id));
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public T find(Long id) {
        final T entity = entityManager.find(type, id);
        if (entity == null) {
            throw new UnprocessableEntityException(type.getSimpleName() + " with id " + id + " not found");
        }
        return entity;
    }

    @Override
    public Collection<T> findAll() {
        return entityManager.createQuery("from " + type.getSimpleName(), type).getResultList();
    }

    @Override
    public void saveAll(List<T> entities) {
        for (T e : entities) {
            entityManager.persist(e);
        }
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("delete " + type.getSimpleName()).executeUpdate();
    }

}
