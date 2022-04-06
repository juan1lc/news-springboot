package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.article.ArticleStar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleStarRepository extends JpaRepository<ArticleStar, String > {

    ArticleStar getArticleStarByArticleidAndUserid(String articleid, String userid);

    Optional<ArticleStar> findByArticleidAndUserid(String articleid, String userid);

    List<ArticleStar> findAllByUseridOrderByLiketimeDesc(String userid);
}
