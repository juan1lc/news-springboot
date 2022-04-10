package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.article.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, String> {

    List<ArticleComment> findByArticleidOrderByCommentlikeDesc(String id);

}
