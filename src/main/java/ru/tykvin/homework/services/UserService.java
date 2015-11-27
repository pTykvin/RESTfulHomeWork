package ru.tykvin.homework.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.tykvin.homework.dao.IUserDao;
import ru.tykvin.homework.domain.Student;

public class UserService implements IUserService {

    private IUserDao dao;


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUser(Student student) {
        dao.save(student);
    }

    public void setDao(IUserDao dao) {
        this.dao = dao;
    }
}
