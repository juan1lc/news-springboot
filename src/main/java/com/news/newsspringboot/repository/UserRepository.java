package com.news.newsspringboot.repository;

import com.news.newsspringboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    //查询用户
    User getByUsername(String username);

    User getByIdAndPassword(String userId, String password);

    Optional<User> findByUsername(String username);

    Page<User> findByUsernameLike(String username, Pageable pageable);
}
