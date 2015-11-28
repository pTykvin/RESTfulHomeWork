package ru.tykvin.homework.dao;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

public interface IRepositoryGeneric<T> {

    @Transactional
    T save(T emp);

    @Transactional
    void delete(T emp);

    @Transactional
    T edit(T emp);

    T find(Long empId);

    Collection<T> findAll();

    void delete(long id);
}