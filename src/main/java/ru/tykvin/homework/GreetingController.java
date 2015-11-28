package ru.tykvin.homework;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityGraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.tykvin.homework.dao.StudentDao;
import ru.tykvin.homework.domain.Student;

@RestController
public class GreetingController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return Help.text;
    }

    @RequestMapping("/students")
    public List<EntityGraph<? super Student>> getAllStudents() {
        return studentDao.getAll();
    }

    @RequestMapping("/students/add")
    public void addStudent(@RequestParam(value="first_name") String firstName, @RequestParam(value="last_name") String lastName) {
        studentDao.persist(new Student(firstName, lastName));
    }

    @RequestMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentDao.get(id);
    }
}