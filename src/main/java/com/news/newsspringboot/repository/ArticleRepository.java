package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, String> {
    Page<Article> findByTitleLike(String title, Pageable pageable);

    @Query(value="select * from Article a order by a.publish_time desc", nativeQuery=true)
    List<Article> findAllByTypeId(String typeid);

    Article getById(String id);
}
