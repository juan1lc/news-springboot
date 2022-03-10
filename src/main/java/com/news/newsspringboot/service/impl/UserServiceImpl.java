package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.entity.User;
import com.news.newsspringboot.repository.UserRepository;
import com.news.newsspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    @Transactional
    public User createUser(User user) {

        return repository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(String userId) {

        repository.delete(getUserById(userId));
    }

    @Transactional
    @Override
    public User editUserName(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setUsername(columnValue);
        repository.save(preuser);
        return repository.getById(userId);

    }

    @Override
    public User editPassword(String userId, String columnValue) {
        User preuser = repository.getById(userId);
        preuser.setPassword(columnValue);
        repository.save(preuser);
        return repository.getById(userId);
    }

    @Override
    public User editPhone(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setPhone(columnValue);
        repository.save(preuser);
        return repository.getById(userId);
    }

    @Override
    public User editMail(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setMail(columnValue);
        repository.save(preuser);
        return repository.getById(userId);
    }

    @Override
    public int editUserPhoto(String userId, String columnValue) {
        User preuser = repository.getById(userId);
        preuser.setPhoto(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public User editUserIntro(String userId, String columnValue) {
        User preuser = repository.getById(userId);
        preuser.setIntroduction(columnValue);
        repository.save(preuser);
        return repository.getById(userId);
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
    public Page<User> search(Pageable pageable) {

        return repository.findAll(pageable);
    }
}
