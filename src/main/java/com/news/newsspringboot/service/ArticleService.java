package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.Article;
import com.news.newsspringboot.model.vo.ArticlePreview;
import com.news.newsspringboot.model.vo.ArticleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArticleService {
    Article publish(Article article);

    void delete(String articleId);

    int editContent(String articleId, String content);

    //模糊查询相关标题的文章
    Page<Article> search(String titleLike,  Pageable pageable);

    List<ArticlePreview> getAllByType(String typeId);

    ArticleVo getArticle(String articleId);
}
