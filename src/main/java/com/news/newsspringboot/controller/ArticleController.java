package com.news.newsspringboot.controller;

import com.news.newsspringboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    ArticleService articleService;


    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
