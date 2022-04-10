package com.news.newsspringboot.controller;


import com.news.newsspringboot.model.dto.ArticleCommentCreateDto;
import com.news.newsspringboot.model.entity.article.ArticleComment;
import com.news.newsspringboot.service.CommentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/articles/comments")
@Api(tags="文章评论")
public class ArticleCommentController {

    CommentService commentService;

    @PostMapping("/add")
    ArticleComment createComment(ArticleCommentCreateDto articleCommentCreateDto){
        return commentService.createArticleComment(articleCommentCreateDto);
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
