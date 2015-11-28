package ru.tykvin.homework.dao;

import org.springframework.stereotype.Repository;

import ru.tykvin.homework.domain.Group;

@Repository
public class RepositoryGroup extends RepositoryGeneric<Group> implements IRepositoryGroup {

    public RepositoryGroup() {
        super(Group.class);
    }

    @Override
    public Group getByNumber(String number) {
        return entityManager.createQuery("from Group where number = :number", Group.class).setParameter("number", number).getSingleResult();
    }

}
