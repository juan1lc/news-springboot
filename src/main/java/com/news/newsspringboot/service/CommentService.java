package com.news.newsspringboot.service;

import com.news.newsspringboot.model.dto.ArticleCommentCreateDto;
import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.entity.article.ArticleComment;
import com.news.newsspringboot.model.entity.post.PostComment;

public interface CommentService {

    //对某动态发表评论
    PostComment createPostComment(PostCommentCreateDto postCommentCreateDto);

    //对某文章发表评论
    ArticleComment createArticleComment(ArticleCommentCreateDto articleCommentCreateDto);

    //删除评论
    void deletePostComment(String postCommentId);
}
