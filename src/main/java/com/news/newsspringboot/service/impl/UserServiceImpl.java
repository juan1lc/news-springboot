package com.news.newsspringboot.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.news.newsspringboot.config.SecurityConfig;
import com.news.newsspringboot.dto.TokenCreateRequest;
import com.news.newsspringboot.entity.User;
import com.news.newsspringboot.exception.BizException;
import com.news.newsspringboot.exception.ExceptionType;
import com.news.newsspringboot.repository.UserRepository;
import com.news.newsspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        checkUserName(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    private void checkUserName(String userName){
        Optional<User> user = repository.findByUsername(userName);
        if(user.isPresent()){
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

    @Override
    public void deleteUser(String userId) {

        repository.delete(getUserById(userId));
    }

    @Transactional
    @Override
    public int editUserName(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setUsername(columnValue);
        repository.save(preuser);
        return 0;

    }

    @Override
    public int editPassword(String userId, String columnValue) {
        User preuser = repository.getById(userId);
        preuser.setPassword(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public int editPhone(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setPhone(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public int editMail(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setMail(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public int editUserPhoto(String userId, String columnValue) {
        User preuser = repository.getById(userId);
        preuser.setPhoto(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public int editUserIntro(String userId, String columnValue) {
        User preuser = repository.getById(userId);
        preuser.setIntroduction(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public User getUserById(String userId) {

        return repository.getById(userId);
    }

    @Override
    public User getUserByUsername(String userName) {

        return repository.getByUsername(userName);
    }

    @Override
    public User findUser(String userId, String password) {

        return repository.getByIdAndPassword(userId, password);
    }

    @Override
    public User findUserByUsername(String userName, String password) {
        User user = getUserByUsername(userName);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        return user;
    }

    @Override
    public Page<User> search(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if(!user.isPresent()){
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();  //optional方法需要get后得到实体
    }

    @Override
    public String createToken(TokenCreateRequest tokenCreateRequest) {
        User user = loadUserByUsername(tokenCreateRequest.getUsername());
        if (!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
    }


    @Override
    public User getCurrentUser() {
        return super.getCurrentUserEntity();
    }
}
