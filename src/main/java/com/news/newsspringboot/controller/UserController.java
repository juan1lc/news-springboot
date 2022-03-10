package com.news.newsspringboot.controller;

import com.news.newsspringboot.entity.User;
import com.news.newsspringboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
//@Api(tags = "用户")
public class UserController {

    UserService userService;

    @GetMapping
    @ApiOperation("用户检索")
    Page<User> search(@PageableDefault(sort = {"username"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable);
    }


    /**
     * 获取创建表单页面
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User create(@Validated User user) {
        return userService.createUser(user);
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


}

