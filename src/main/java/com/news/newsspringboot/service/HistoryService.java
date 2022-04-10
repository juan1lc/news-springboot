package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.article.ArticleHistory;
import com.news.newsspringboot.model.entity.post.PostHistory;
import com.news.newsspringboot.model.vo.ArticleHistoryVo;
import com.news.newsspringboot.model.vo.PostHistoryVo;

import java.util.List;

public interface HistoryService {
    ArticleHistory create(String userid, String articleid);

    List<ArticleHistoryVo> getArticleHistoryVos(String userid);

    PostHistory createPostHistory(String userid, String postid);

    List<PostHistoryVo> getPostHistoryVos(String userid);
}
