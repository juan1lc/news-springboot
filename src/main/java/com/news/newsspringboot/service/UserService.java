package com.news.newsspringboot.service;

import com.news.newsspringboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    User createUser(User user);
    //删除用户
    void deleteUser(String userId);
    //修改用户信息
    User editUserName(String userId, String columnValue);
    User editPassword(String userId, String columnValue);
    User editPhone(String userId, String columnValue);
    User editMail(String userId, String columnValue);
    int editUserPhoto(String userId,String columnValue);
    User editUserIntro(String userId, String columnValue);
    //查询用户信息
    User getUserById(String userId);
    User getUserByUsername(String userName);
    //身份验证
    User findUser(String userId,String password);
    //查询所有用户信息
    Page<User> search(Pageable pageable);

}
