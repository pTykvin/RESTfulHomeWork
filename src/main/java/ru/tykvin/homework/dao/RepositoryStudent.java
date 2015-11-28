package ru.tykvin.homework.dao;

import org.springframework.stereotype.Repository;

import ru.tykvin.homework.domain.Student;

@Repository
public class RepositoryStudent extends RepositoryGeneric<Student> implements IRepositoryStudent {

    public RepositoryStudent() {
        super(Student.class);
    }

}
