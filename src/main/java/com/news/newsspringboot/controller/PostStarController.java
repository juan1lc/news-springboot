package com.news.newsspringboot.controller;

import com.news.newsspringboot.service.PostStarService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/star-posts")
public class PostStarController {
    PostStarService postStarService;

    @GetMapping(value = "/star/{postid}/{userid}", produces = "application/json;charset=utf-8")
    boolean staredPost(@PathVariable(value = "postid") String postid,
                          @PathVariable(value = "userid") String userid){
        return postStarService.checkStarTable(postid, userid);
    }

    @PostMapping("/star")
    Integer starArticle (@RequestParam(value = "postid") String postid,
                         @RequestParam(value = "userid") String userid) {
        return postStarService.StarUnstar(postid, userid);
    }

    @Autowired
    public void setPostStarService(PostStarService postStarService) {
        this.postStarService = postStarService;
    }
}
