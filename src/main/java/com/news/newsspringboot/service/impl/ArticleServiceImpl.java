package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.Article;
import com.news.newsspringboot.repository.ArticleRepository;
import com.news.newsspringboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository repository;

    @Override
    public Article publish(Article article) {
        return repository.save(article);
    }

    @Override
    public void delete(String articleId) {
        repository.deleteById(articleId);
    }

    @Override
    public int editContent(String articleId, String content){
        Article preArticle = repository.getById(articleId);
        preArticle.setContent(content);
        return 0;
    }

    @Override
    public Page<Article> search(String titleLike, Pageable pageable) {
        return repository.findByTitleLike(titleLike, pageable);
    }
}
