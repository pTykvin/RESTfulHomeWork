package ru.tykvin.homework.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.tykvin.homework.dao.IUserDao;
import ru.tykvin.homework.domain.User;

public class UserService implements IUserService {

    private IUserDao dao;


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        dao.save(user);
    }

    public void setDao(IUserDao dao) {
        this.dao = dao;
    }
}
