package com.news.newsspringboot.controller.passport;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/register")
@Api(tags = "注册")
public class RegisterController {
    UserService userService;

    @PostMapping
    User create(@Validated User user) {
        return userService.createUser(user);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
