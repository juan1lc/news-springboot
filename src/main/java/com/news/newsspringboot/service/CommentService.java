package com.news.newsspringboot.service;

import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.entity.comment.PostComment;

public interface CommentService {

    //对某动态发表评论
    PostComment create(PostCommentCreateDto postCommentCreateDto, String postId);

    //删除评论
    void delete(String postCommentId);
}
