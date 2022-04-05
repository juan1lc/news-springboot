package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.ArticleLike;

import java.util.List;

public interface ArticleLikeService {

    boolean CheckLikeTable(String articleId, String userId);

    List<ArticleLike> getAllLikers(String articleid);

    Integer LikeDislkeArticle(String articleid, String userid);
}
