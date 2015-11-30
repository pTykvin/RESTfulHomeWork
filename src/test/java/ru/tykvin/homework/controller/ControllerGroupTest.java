package ru.tykvin.homework.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
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
import ru.tykvin.homework.dao.IRepositoryGroup;
import ru.tykvin.homework.dao.IRepositoryStudent;
import ru.tykvin.homework.domain.Group;
import ru.tykvin.homework.domain.Student;
import ru.tykvin.homework.service.IServiceGroupOperations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ControllerGroupTest {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private IRepositoryGroup repositoryGroup;

    @Autowired
    private IRepositoryStudent repositoryStudent;

    private Student student1;
    private Student student2;
    private Group group1;
    private Group group2;

    private RestTemplate restTemplate = new TestRestTemplate();

    @Value("${local.server.port}")
    int port;

    @Before
    public void beforeEach() {
        student1 = new Student("Ivan", "Ivanov");
        student2 = new Student("Petr", "Petrov");
        group1 = new Group("2350", null);
        group2 = new Group("3350", null);

        repositoryStudent.deleteAll();
        repositoryGroup.deleteAll();

        repositoryStudent.saveAll(Arrays.asList(student1, student2));
        repositoryGroup.saveAll(Arrays.asList(group1, group2));
    }

    @Test
    public void testGetAllGroups() {
        Group[] response = get("/group", Group[].class);
        assertEquals(2, response.length);
        assertTrue(contains(response, group1));
        assertTrue(contains(response, group2));
    }

    @Test
    public void testCreateGroup() throws Exception {
        Group group = new Group("4350", "test department");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(group), requestHeaders);

        Group response = post("/group", request);
        assertNotNull(response);
        Group fromDb = repositoryGroup.find(response.getId());
        groupEquals(fromDb, response);
        repositoryGroup.delete(response.getId());
    }

    @Test
    public void testGetGroupById() {
        Group response = get("/group/" + group1.getId(), Group.class);
        Group fromDb = repositoryGroup.find(group1.getId());
        groupEquals(fromDb, response);
    }

    @Test
    public void testDeleteGroup() throws Exception {
        Group beforeDelete = get("/group/" + group1.getId(), Group.class);
        assertNotNull(beforeDelete);
        delete("/group/" + group1.getId());
        Group afterDelete = get("/group/" + group1.getId(), Group.class);
        assertNull(afterDelete);
    }

    @Test
    public void testMoveStudent() throws Exception {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<>("student_id=" + student1.getId(), requestHeaders);
        put("/group/" + group1.getId() + "/student", request);
        Group modifiedGroup = get("/group/" + group1.getId(), Group.class);
        assertEquals(1, modifiedGroup.getStudents().size());
        assertEquals(student1, modifiedGroup.getStudents().iterator().next());
    }

    @Test
    public void testRemoveStudent() throws Exception {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<>("student_id=" + student1.getId(), requestHeaders);
        put("/group/" + group1.getId() + "/student", request);
        Group modifiedGroup = get("/group/" + group1.getId(), Group.class);
        assertEquals(1, modifiedGroup.getStudents().size());
        assertEquals(student1, modifiedGroup.getStudents().iterator().next());
        delete("/group/" + group1.getId() + "/student/" + student1.getId());
        Group emptyGroup = get("/group/" + group1.getId(), Group.class);
        assertTrue(emptyGroup.getStudents().isEmpty());
    }

    private boolean contains(Group[] response, Group group) {
        for (Group g : response) {
            if (g.getId() == group.getId()) {
                return true;
            }
        }
        return false;
    }

    private void groupEquals(Group fromDb, Group response) {
        assertEquals(fromDb.getId(), response.getId());
        assertEquals(fromDb.getNumber(), response.getNumber());
        assertEquals(fromDb.getDepartment(), response.getDepartment());
    }

    private void delete(String path) throws URISyntaxException {
        restTemplate.delete(new URI(getFullPath(path)));
    }

    private <T> T get(String path, Class<T> clazz) {
        return restTemplate.getForObject(getFullPath(path), clazz);
    }

    private Group post(String path, HttpEntity<String> request) {
        return restTemplate.postForObject(getFullPath(path), request, Group.class);
    }

    private void put(String path, HttpEntity<String> request) {
        restTemplate.put(getFullPath(path), request);
    }

    private String getFullPath(String path) {
        return "http://localhost:" + port + path;
    }


}



