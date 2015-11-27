package ru.tykvin.homework.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import ru.tykvin.homework.domain.User;

public class UserDao extends HibernateDaoSupport implements IUserDao {

    @Override
    public void save(User user) {
        getHibernateTemplate().save(user);
    }
}
