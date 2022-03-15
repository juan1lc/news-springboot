package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.vo.Response;
import com.news.newsspringboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/users")
@Api(tags = "用户")
public class UserController {

    UserService userService;

    @GetMapping
    @ApiOperation("用户检索")
    @RolesAllowed({"ROLE_ADMIN"})
    Page<User> search(@PageableDefault(sort = {"username"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable);
    }

    @PostMapping
    User create(@Validated @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    User get(@PathVariable String id) {
        return userService.getUserById(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable String id) {
        userService.deleteUser(id);
    }


    @GetMapping("info/{id}")
    Response getUserInfoById(@PathVariable("id") String userId){
        log.info("#### 获取指定用户的信息，入参：userId={}",userId);
        User user = userService.getUserById(userId);
        log.info("#### 获取指定用户的信息，结果：user={}",user);
        if(Objects.isNull(user)){
            return Response.responseFail("用户ID无效",null);
        }
        return Response.responseSuccess("获取成功",user);
    }

    /**
     * 修改用户密码
     * @param userName    用户名
     * @param password  原密码
     * @param newPassword   新密码
     * @return 返回业务响应对象（包含数据）
     */
    @PutMapping("safe/password/modify")
    Response modifyUserPassword(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword){
        log.info("#### 用户密码修改API，入参：userName={}，password={}，newPassword={}",userName,password,newPassword);
        boolean  b = userService.modifyUserPassword(userName, password, newPassword);
        if(!b){
            log.info("#### 用户密码修改API，修改密码失败！");
            return Response.responseFail("修改失败！",null);
        }
        log.info("#### 用户密码修改API，修改密码成功！");
        return Response.responseSuccess("修改成功！",null);
    }


    @GetMapping(value="/login",produces = "application/json;charset=utf-8")
    public User findUser(@RequestParam(value="username") String username,@RequestParam(value="password") String password){
        log.info("#### 用户登录，入参：username={}",username);
        if(userService.findUserByUsername(username,password)!=null) {
            return userService.findUserByUsername(username,password);
        }
        else {
            User user=new User();
            return user;
        }
    }


    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


}

