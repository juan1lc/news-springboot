package com.news.newsspringboot.repository;

import com.news.newsspringboot.entity.User;
import com.news.newsspringboot.repository.UserRepository;
import com.news.newsspringboot.utils.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    void findByUsername(){
        User user = new User();
        user.setUsername("刘宇");
        user.setPassword("0824");
        user.setGender(Gender.MALE);
        user.setPhoto("src/main/resources/static/images/defaulthead.jpg");

        User saveduser = repository.save(user);

        User result = repository.getByUsername("刘宇");
        System.out.println(result.toString());
    }
}
