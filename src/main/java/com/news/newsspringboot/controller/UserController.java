package com.news.newsspringboot.controller;

import com.news.newsspringboot.entity.User;
import com.news.newsspringboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api(tags = "用户")
public class UserController {

    UserService userService;

    @GetMapping
    @ApiOperation("用户检索")
    Page<User> search(@PageableDefault(sort = {"username"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User create(@Validated @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User get(@PathVariable String id) {
        return userService.getUserById(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    int update( @PathVariable String id,
                            @RequestParam(value="columnName") String columnName,
                            @RequestParam(value="columnValue") String columnValue) {
        if (columnName.equals("userName")) {
            userService.editUserName(id, columnValue);
            return 0;
        }
        if (columnName.equals("password")) {
            userService.editPassword(id, columnValue);
            return 0;
        }
        if (columnName.equals("phone")) {
            userService.editPhone(id, columnValue);
            return 0;
        }
        if (columnName.equals("mail")) {
            userService.editMail(id, columnValue);
            return 0;
        }
        if (columnName.equals("Photo")) {
            userService.editUserPhoto(id, columnValue);
            return 0;
        }
        if(columnName.equals("Introduction")){
            userService.editUserIntro(id, columnValue);
            return 0;
        }
        return 1;
    }


    @GetMapping(value="/login",produces = "application/json;charset=utf-8")
    public User findUser(@RequestParam(value="username") String username,@RequestParam(value="password") String password){
        if(userService.findUserByUsername(username,password)!=null) {
            return userService.findUserByUsername(username,password);
        }
        else {
            User user=new User();
            return user;
        }
    }

    //根据tokens信息得到个人信息
    @GetMapping("/me")
    User me() {
        return userService.getCurrentUser();
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


}

