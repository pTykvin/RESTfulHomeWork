package ru.tykvin.homework.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.tykvin.homework.Application;
import ru.tykvin.homework.dao.IRepositoryStudent;
import ru.tykvin.homework.domain.Group;
import ru.tykvin.homework.domain.Student;
import ru.tykvin.homework.exception.UnprocessableEntityException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ControllerStudentTest {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private IRepositoryStudent repository;

    private RestTemplate restTemplate = new TestRestTemplate();
    private Student student1;
    private Student student2;

    @Value("${local.server.port}")
    int port;

    @Before
    public void beforeEach() {
        student1 = new Student("Ivan", "Ivanov");
        student2 = new Student("Petr", "Petrov");

        repository.deleteAll();
        repository.saveAll(Arrays.asList(student1, student2));
    }

    @Test
    public void testGetAllStudents() {
        Student[] response = get("/student", Student[].class);
        assertEquals(2, response.length);
        assertTrue(ArrayUtils.contains(response, student1));
        assertTrue(ArrayUtils.contains(response, student2));
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student newStudent = new Student("Sidor", "Sidorov");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(newStudent), requestHeaders);

        Student response = post("/student", request);
        assertNotNull(response);
        Student fromDb = repository.find(response.getId());
        assertEquals(response, fromDb);
        repository.delete(response.getId());
    }

    @Test
    public void testGetStudentById() {
        Student response = get("/student/" + student1.getId(), Student.class);
        Student fromDb = repository.find(student1.getId());
        assertEquals(response, fromDb);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student beforeDelete = get("/student/" + student1.getId(), Student.class);
        assertNotNull(beforeDelete);
        delete("/student/" + student1.getId());
        try {
            get("/student/" + student1.getId(), Student.class);
        } catch (UnprocessableEntityException e) {
            assertEquals(Student.class.getSimpleName() + " with id " + student1.getId() + " not found", e.getMessage());
        }
    }

    private void delete(String path) throws URISyntaxException {
        restTemplate.delete(new URI(getFullPath(path)));
    }

    private <T> T get(String path, Class<T> clazz) {
        return restTemplate.getForObject(getFullPath(path), clazz);
    }

    private Student post(String path, HttpEntity<String> request) {
        return restTemplate.postForObject(getFullPath(path), request, Student.class);
    }

    private String getFullPath(String path) {
        return "http://localhost:" + port + path;
    }


}


