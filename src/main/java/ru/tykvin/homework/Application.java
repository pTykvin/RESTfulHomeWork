package ru.tykvin.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.tykvin.homework.services.IUserService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        IUserService service = (IUserService) ctx.getBean("userService");
        SpringApplication.run(Application.class, args);
    }
}