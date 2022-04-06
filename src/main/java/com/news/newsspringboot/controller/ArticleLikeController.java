package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.entity.article.ArticleLike;
import com.news.newsspringboot.service.ArticleLikeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/like-articles")
public class ArticleLikeController {

    ArticleLikeService articleLikeService;

    @GetMapping(value = "/all-likers/{articleid}", produces = "application/json;charset=utf-8")
    List<ArticleLike> getLikers(@PathVariable(value = "articleid") String article_id){
        return articleLikeService.getAllLikers(article_id);
    }

    @GetMapping(value = "/like/{articleid}/{userid}", produces = "application/json;charset=utf-8")
    boolean likedArticle(@PathVariable(value = "articleid") String articleid,
                         @PathVariable(value = "userid") String userid){
        return articleLikeService.CheckLikeTable(articleid, userid);
    }

    @PostMapping("/like")
    Integer likeArticle (@RequestParam(value = "articleid") String articleid,
                    @RequestParam(value = "userid") String userid) {
        return articleLikeService.LikeDislkeArticle(articleid, userid);
    }

    @Autowired
    public void setArticleLikeService(ArticleLikeService articleLikeService) {
        this.articleLikeService = articleLikeService;
    }
}
