package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.entity.article.ArticleSecondComment;
import com.news.newsspringboot.model.vo.SecondCommentVo;

public interface ArticleSecondCommentMapper {
    SecondCommentVo toVo(ArticleSecondComment articleSecondComment);
}
