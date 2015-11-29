package ru.tykvin.homework.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.tykvin.homework.dao.IRepositoryGroup;
import ru.tykvin.homework.dao.IRepositoryStudent;
import ru.tykvin.homework.domain.Group;

@Service
public class ServiceGroupOperations implements IServiceGroupOperations{

    @Autowired
    private IRepositoryStudent studentRepository;

    @Autowired
    private IRepositoryGroup groupRepository;

    @Override
    public void removeStudentFromGroup(long groupId, long student) {
        Group group = groupRepository.find(groupId);
        group.getStudents().remove(studentRepository.find(student));
        groupRepository.update(group);
    }

    @Override
    public void moveStudentToGroup(long groupId, long student) {
        Group group = groupRepository.find(groupId);
        group.getStudents().add(studentRepository.find(student));
        groupRepository.update(group);
    }

}
