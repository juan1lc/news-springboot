package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.dto.ArticleCommentCreateDto;
import com.news.newsspringboot.model.entity.article.ArticleComment;
import com.news.newsspringboot.model.vo.ArticleCommentVo;

public interface ArticleCommentMapper {

    ArticleComment articleCommentCreateEntity(ArticleCommentCreateDto articleCommentCreateDto);

    ArticleCommentVo toVo(ArticleComment articleComment);
}
