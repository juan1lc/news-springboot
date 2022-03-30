package com.news.newsspringboot.controller.passport;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.mapper.UserMapper;
import com.news.newsspringboot.model.vo.UserVo;
import com.news.newsspringboot.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
@Api(tags = "登录")
public class LoginController {
    UserService userService;
    UserMapper userMapper;

    @PostMapping(value = "/by-name", produces = "application/json;charset=utf-8")
    public UserVo findUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password){
        log.info("#### 用户登录，入参：username={}",username);
        if(userService.findUserByUsername(username,password)!=null) {
            return userMapper.toUserVo(userService.findUserByUsername(username,password));
        }
        else {
            UserVo user=new UserVo();
            return user;
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
