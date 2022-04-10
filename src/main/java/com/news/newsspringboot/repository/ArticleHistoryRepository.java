package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.article.ArticleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleHistoryRepository extends JpaRepository<ArticleHistory, String> {

    Optional<ArticleHistory> findByArticleidAndUserid(String articleId, String userid);

    List<ArticleHistory> findAllByUseridOrderByTimeDesc(String userid);

    ArticleHistory getArticleHistoryByArticleidAndUserid(String articleid, String userid);
}
