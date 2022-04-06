package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.entity.article.ArticleLike;
import com.news.newsspringboot.model.entity.article.ArticleStar;
import com.news.newsspringboot.service.ArticleStarService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/star-articles")
@Api(tags="文章")
public class ArticleStarController {
    ArticleStarService articleStarService;

    @GetMapping(value = "/star/{articleid}/{userid}", produces = "application/json;charset=utf-8")
    boolean staredArticle(@PathVariable(value = "articleid") String articleid,
                         @PathVariable(value = "userid") String userid){
        return articleStarService.CheckStarTable(articleid, userid);
    }

    @PostMapping("/star")
    Integer starArticle (@RequestParam(value = "articleid") String articleid,
                         @RequestParam(value = "userid") String userid) {
        return articleStarService.StarUnstar(articleid, userid);
    }

    @Autowired
    public void setArticleStarService(ArticleStarService articleStarService) {
        this.articleStarService = articleStarService;
    }
}
