package ru.tykvin.homework.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.tykvin.homework.Help;
import ru.tykvin.homework.dao.IRepositoryGroup;
import ru.tykvin.homework.domain.Group;
import ru.tykvin.homework.domain.Student;
import ru.tykvin.homework.service.IServiceGroupOperations;

@RestController
public class ControllerGroup {

    @Autowired
    private IRepositoryGroup groupRepository;

    @Autowired
    private IServiceGroupOperations operations;

    @RequestMapping("/")
    public String startPage() throws Exception {
        return Help.text;
    }

    @RequestMapping("/group")
    public Collection<Group> getAllGroups() {
        return groupRepository.findAll();
    }


    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public Group addGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    @RequestMapping("/group/{id}")
    public Group getGroup(@PathVariable long id) {
        return groupRepository.find(id);
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    public void deleteGroup(@PathVariable long id) {
        groupRepository.delete(id);
    }

    @RequestMapping(value = "/group/{id}/student/{student_id}", method = RequestMethod.PUT)
    public void moveStudent(@PathVariable long id, @PathVariable long student_id) {
        operations.moveStudentToGroup(id, student_id);
    }

    @RequestMapping(value = "/group/{id}/student/{student_id}", method = RequestMethod.DELETE)
    public void removeStudent(@PathVariable long id, @PathVariable long student_id) {
        operations.removeStudentFromGroup(id, student_id);
    }

}