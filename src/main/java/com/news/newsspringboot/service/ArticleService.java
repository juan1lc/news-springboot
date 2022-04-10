package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.article.Article;
import com.news.newsspringboot.model.vo.ArticleCommentVo;
import com.news.newsspringboot.model.vo.ArticleLikePreview;
import com.news.newsspringboot.model.vo.ArticlePreview;
import com.news.newsspringboot.model.vo.ArticleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArticleService {
    Article publish(Article article);

    void delete(String articleId);

    int editContent(String articleId, String content);

    //模糊查询相关标题的文章
    Page<Article> search(String titleLike,  Pageable pageable);

    List<ArticlePreview> getAllByType(String typeId);

    List<ArticleLikePreview> getUserLike(String userId);

    List<ArticleLikePreview> getUserStar(String userId);

    List<ArticleCommentVo> getAllComments(String articleId);

    ArticleVo getArticle(String articleId);

    List<ArticleVo> getReconmendArticles(String userId);
}
