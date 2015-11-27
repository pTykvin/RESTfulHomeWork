package ru.tykvin.homework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ru.tykvin.homework.domain.Student;


@Service("StudentService")
public class StudentService {

    public List<Student> getAllStudents() {
        List<Student> Students = new ArrayList<Student>();

        Student student = new Student();
        student.setFirst_name("Pavel");
        student.setLast_name("Tykvin");
        Students.add(student);

        student = new Student();
        student.setFirst_name("Ivan");
        student.setLast_name("Ivanov");
        Students.add(student);

        return Students;
    }

    public Student getStudentById(int StudentId) {
        Student student = null;
        if (StudentId == 1) {
            student = new Student();
            student.setFirst_name("Pavel");
            student.setLast_name("Tykvin");
        }else{
            student = new Student();
            student.setFirst_name("Ivan");
            student.setLast_name("Ivanov");
        }
        return student;
    }
}
