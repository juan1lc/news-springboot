package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.dto.SecondCommentCreateDto;
import com.news.newsspringboot.model.entity.article.ArticleSecondComment;
import com.news.newsspringboot.model.entity.post.PostSecondComment;
import com.news.newsspringboot.model.vo.SecondCommentVo;
import com.news.newsspringboot.service.SecondCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/second-comments")
public class SecondCommentController {

    SecondCommentService secondCommentService;

    @PostMapping(value = "/add/article/{comment-id}", produces = "application/json;charset=utf-8")
    ArticleSecondComment createArticleSecondComment(SecondCommentCreateDto CommentCreateDto) {
        return secondCommentService.createArticleSecondComment(CommentCreateDto);
    }
    @PostMapping(value = "/add/post/{comment-id}", produces = "application/json;charset=utf-8")
    PostSecondComment createPostSecondComment(SecondCommentCreateDto CommentCreateDto) {
        return secondCommentService.createPostSecondComment(CommentCreateDto);
    }


    @GetMapping(value = "/article/{comment-id}", produces = "application/json;charset=utf-8")
    List<SecondCommentVo> getArticleSecondComments(@PathVariable(value = "comment-id") String commentid){
        return secondCommentService.getArticleSecondCommentList(commentid);
    }
    @GetMapping(value = "/post/{comment-id}", produces = "application/json;charset=utf-8")
    List<SecondCommentVo> getPostSecondComments(@PathVariable(value = "comment-id") String commentid){
        return secondCommentService.getPostSecondCommentList(commentid);
    }

    @Autowired
    public void setSecondCommentService(SecondCommentService secondCommentService) {
        this.secondCommentService = secondCommentService;
    }
}
