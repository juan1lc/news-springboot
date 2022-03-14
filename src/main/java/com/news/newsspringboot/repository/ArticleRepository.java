package com.news.newsspringboot.repository;

import com.news.newsspringboot.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, String> {
    Page<Article> findByTitleLike(String title, Pageable pageable);

}
