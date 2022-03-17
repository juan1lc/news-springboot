package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.service.CommentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/posts/comments")
@Api(tags="动态评论")
public class PostCommentController {

    CommentService commentService;

    @PostMapping("/add/{id}")
    PostComment createComment(@PathVariable("id") String postId, PostCommentCreateDto postCommentCreateDto){
        return commentService.create(postCommentCreateDto, postId);
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
