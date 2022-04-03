package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.entity.Article;
import com.news.newsspringboot.model.vo.ArticlePreview;
import com.news.newsspringboot.model.vo.ArticleVo;

public interface ArticleMapper {
    ArticleVo toVo(Article article);

    ArticlePreview toPreview(Article article);
}
