package com.news.newsspringboot.service;

import com.news.newsspringboot.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ArticleService {
    Article publish(Article article);

    void delete(String articleId);

    int editContent(String articleId, String content);

    //模糊查询相关标题的文章
    Page<Article> search(String titleLike,  Pageable pageable);
}
