package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.entity.article.ArticleHistory;
import com.news.newsspringboot.model.entity.post.PostHistory;
import com.news.newsspringboot.model.vo.ArticleHistoryVo;
import com.news.newsspringboot.model.vo.PostHistoryVo;
import com.news.newsspringboot.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    HistoryService historyService;

    @PostMapping("/article/{article-id}/{user-id}")
    ArticleHistory createHistory(@PathVariable(value = "article-id") String articleId,
                                 @PathVariable(value = "user-id") String userId){
        return historyService.create(userId, articleId);
    }

    @GetMapping(value = "/article/{user-id}", produces = "application/json;charset=utf-8")
    List<ArticleHistoryVo> getArticleHistoryList(@PathVariable(value = "user-id") String userId){
        return historyService.getArticleHistoryVos(userId);
    }

    @PostMapping("/post/{post-id}/{user-id}")
    PostHistory createPostHistory(@PathVariable(value = "post-id") String postId,
                                  @PathVariable(value = "user-id") String userId){
        return historyService.createPostHistory(userId, postId);
    }

    @GetMapping(value = "/post/{user-id}", produces = "application/json;charset=utf-8")
    List<PostHistoryVo> getPostHistoryList(@PathVariable(value = "user-id") String userId){
        return historyService.getPostHistoryVos(userId);
    }


    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }
}
