package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.article.ArticleSecondComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleSecondCommentRepository extends JpaRepository<ArticleSecondComment, String> {

    List<ArticleSecondComment> findByCommentidOrderByCommentlikeDesc(String commentid);
}
