package com.news.newsspringboot.service;

import com.news.newsspringboot.model.dto.SecondCommentCreateDto;
import com.news.newsspringboot.model.entity.article.ArticleSecondComment;
import com.news.newsspringboot.model.entity.post.PostSecondComment;
import com.news.newsspringboot.model.vo.SecondCommentVo;

import java.util.List;

public interface SecondCommentService {

    ArticleSecondComment createArticleSecondComment(SecondCommentCreateDto secondCommentCreateDto);

    List<SecondCommentVo> getArticleSecondCommentList(String commentid);

    PostSecondComment createPostSecondComment(SecondCommentCreateDto secondCommentCreateDto);

    List<SecondCommentVo> getPostSecondCommentList(String commentid);
}
