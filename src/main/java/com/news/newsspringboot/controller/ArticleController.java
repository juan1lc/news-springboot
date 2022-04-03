package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.mapper.ArticleMapper;
import com.news.newsspringboot.model.vo.ArticlePreview;
import com.news.newsspringboot.model.vo.ArticleVo;
import com.news.newsspringboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    ArticleService articleService;

    @GetMapping(value = "/all/{type-id}", produces = "application/json;charset=utf-8")
    List<ArticlePreview> getArticles(@PathVariable(value = "type-id") String typeId){
        return articleService.getAllByType(typeId);
    }

    @GetMapping(value = "/detail/{article-id}", produces = "application/json;charset=utf-8")
    ArticleVo getArticle(@PathVariable(value = "article-id") String articleId){
        return articleService.getArticle(articleId);
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

}
