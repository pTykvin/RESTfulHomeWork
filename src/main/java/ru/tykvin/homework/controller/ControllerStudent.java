package ru.tykvin.homework.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.tykvin.homework.dao.IRepositoryStudent;
import ru.tykvin.homework.domain.Student;

@RestController
public class ControllerStudent {

    @Autowired
    private IRepositoryStudent studentRepository;

    @RequestMapping("/student")
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @RequestMapping("/student/{id}")
    public Student getStudent(@PathVariable long id) {
        return studentRepository.find(id);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable long id) {
        studentRepository.delete(id);
    }

}