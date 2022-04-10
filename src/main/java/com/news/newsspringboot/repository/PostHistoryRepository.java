package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.post.PostHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostHistoryRepository extends JpaRepository<PostHistory, String> {

    Optional<PostHistory> findByUseridAndPostid(String userid, String postid);

    List<PostHistory> findAllByUseridOrderByTimeDesc(String userid);

    PostHistory getPostHistoryByUseridAndPostid(String userid, String postid);
}
