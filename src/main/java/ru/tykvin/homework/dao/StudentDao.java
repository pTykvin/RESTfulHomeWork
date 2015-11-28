package ru.tykvin.homework.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.tykvin.homework.domain.Student;

@Component
public class StudentDao {

    @PersistenceContext
    private EntityManager manager;

    public Student get(long id) {
        return manager.find(Student.class, id);
    }

    @Transactional
    public void persist(Student student) {
        manager.persist(student);
    }

    public List<EntityGraph<? super Student>> getAll() {
        return manager.getEntityGraphs(Student.class);
    }

}
