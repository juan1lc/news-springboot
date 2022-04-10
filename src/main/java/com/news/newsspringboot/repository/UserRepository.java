package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    User getUserByUsername(String username);


    User getByIdAndPassword(String userId, String password);


    Optional<User> findByUsername(String username);


    Optional<User> findUserByUsernameAndPassword(String username, String password);


    @Query("select u from User u where u.username like %?1%")
    List<User> findByUsernameLike(String username);

}
