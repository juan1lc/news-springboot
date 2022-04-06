package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.article.ArticleStar;

public interface ArticleStarService {

    boolean CheckStarTable(String articleid, String userid);

    Integer StarUnstar(String articleid, String userid);
}
