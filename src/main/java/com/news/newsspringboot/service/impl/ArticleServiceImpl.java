package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.Article;
import com.news.newsspringboot.model.mapper.ArticleMapper;
import com.news.newsspringboot.model.vo.ArticlePreview;
import com.news.newsspringboot.model.vo.ArticleVo;
import com.news.newsspringboot.repository.ArticleRepository;
import com.news.newsspringboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository repository;
    ArticleMapper articleMapper;

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

    @Override
    public List<ArticlePreview> getAllByType(String typeId) {
        List<Article> list;
        List<ArticlePreview> listPreview = new ArrayList<>();

        list = repository.findAllByTypeId(typeId);
        for(Article article : list){
            listPreview.add(articleMapper.toPreview(article));
        }
        return listPreview;
    }

    @Override
    public ArticleVo getArticle(String articleId) {
        return articleMapper.toVo(repository.getById(articleId));
    }

    @Autowired
    public void setRepository(ArticleRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }
}
