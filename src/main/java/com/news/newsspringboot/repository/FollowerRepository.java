package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, String> {

    List<Follower> findAllByUserid1(String user1);

    List<Follower> findAllByUserid2(String user2);

    Optional<Follower> findByUserid1AndUserid2(String user1, String user2);

    Follower getFollowerByUserid1AndUserid2(String user1, String user2);

}