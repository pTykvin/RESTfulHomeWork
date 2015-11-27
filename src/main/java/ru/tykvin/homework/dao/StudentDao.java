package ru.tykvin.homework.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import ru.tykvin.homework.domain.Student;

public class StudentDao extends HibernateDaoSupport implements IUserDao {

    @Override
    public void save(Student student) {
        getHibernateTemplate().save(student);
    }
}
