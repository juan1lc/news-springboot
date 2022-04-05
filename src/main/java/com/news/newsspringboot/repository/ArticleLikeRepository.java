package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, String > {

    ArticleLike getArticleLikeByArticleidAndUserid(String articleid, String userid);
    Optional<ArticleLike> findByArticleidAndUserid(String articleid, String userid);
    List<ArticleLike> findAllByArticleid(String articleid);
}
