package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.post.PostStar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostStarRepository extends JpaRepository<PostStar, String > {
    PostStar getPostStarByPostidAndUserid(String postid, String userid);

    Optional<PostStar> findByPostidAndUserid(String postid, String userid);

    List<PostStar> findAllByUseridOrderByLiketimeDesc(String userid);
}
