package ru.tykvin.homework.service;

public interface IServiceGroupOperations {
    void removeStudentFromGroup(long student, long groupId);

    void moveStudentToGroup(long student, long groupId);

}
