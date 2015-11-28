package ru.tykvin.homework.dao;

import ru.tykvin.homework.domain.Group;

public interface IRepositoryGroup extends IRepositoryGeneric<Group> {
    Group getByNumber(String number);
}
