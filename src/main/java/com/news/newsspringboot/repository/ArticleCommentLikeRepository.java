package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.article.ArticleCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleCommentLikeRepository extends JpaRepository<ArticleCommentLike, String> {

    Optional<ArticleCommentLike> findByUseridAndCommentid(String userid, String commentid);
    ArticleCommentLike getArticleCommentLikeByCommentidAndUserid(String commentid, String userid);
    List<ArticleCommentLike> findAllByCommentid(String commentid);
}
