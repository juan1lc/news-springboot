package com.news.newsspringboot.service;

import com.news.newsspringboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    User createUser(User user);
    //删除用户
    void deleteUser(String userId);
    //修改用户信息
    int editUserName(String userId, String columnValue);
    int editPassword(String userId, String columnValue);
    int editPhone(String userId, String columnValue);
    int editMail(String userId, String columnValue);
    int editUserPhoto(String userId,String columnValue);
    int editUserIntro(String userId, String columnValue);
    //查询用户信息
    User getUserById(String userId);
    //身份验证
    User getUserByUsername(String userName);
    User findUser(String userId,String password);
    //查询所有用户信息
    Page<User> search(Pageable pageable);

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;
}
