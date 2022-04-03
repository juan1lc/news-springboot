package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, String > {

    PostLike getLikePostByUseridAndPostid(String userid, String postid);
    Optional<PostLike> findByPostidAndUserid(String postid, String userid);
    List<PostLike> findAllByPostid(String postid);
}
