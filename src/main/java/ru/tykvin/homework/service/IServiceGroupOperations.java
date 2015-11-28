package ru.tykvin.homework.service;

import ru.tykvin.homework.domain.Student;

public interface IServiceGroupOperations {
    void removeStudentFromGroup(long student, long groupId);

    void moveStudentToGroup(long student, long groupId);

}
