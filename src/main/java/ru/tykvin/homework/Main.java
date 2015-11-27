package ru.tykvin.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.tykvin.homework.domain.User;
import ru.tykvin.homework.services.IUserService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService service = (IUserService) ctx.getBean("userService");
        User user = new User();
        user.setFirst_name("Pavel");
        user.setLast_name("Tykvin");
        service.saveUser(user);
    }

}
