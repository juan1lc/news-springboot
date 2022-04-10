package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.entity.article.Article;
import com.news.newsspringboot.model.entity.article.ArticleHistory;
import com.news.newsspringboot.model.vo.ArticleHistoryVo;
import com.news.newsspringboot.model.vo.ArticleLikePreview;
import com.news.newsspringboot.model.vo.ArticlePreview;
import com.news.newsspringboot.model.vo.ArticleVo;

import java.util.Date;

public interface ArticleMapper {
    ArticleVo toVo(Article article);

    ArticlePreview toPreview(Article article);

    ArticleLikePreview toLikePreview(Article article, Date liketime);

    ArticleHistoryVo toHistoryVo(ArticleHistory articleHistory);
}
