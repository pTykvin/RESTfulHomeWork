package ru.tykvin.homework.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ru.tykvin.homework.domain.Student;

public interface IRepositoryGeneric<T> {

    @Transactional
    T save(T entity);

    @Transactional
    void delete(T entity);

    @Transactional
    T update(T entity);

    @Transactional
    void delete(long id);

    @Transactional
    void saveAll(List<T> entities);

    @Transactional
    void deleteAll();

    T find(Long id);

    Collection<T> findAll();
}