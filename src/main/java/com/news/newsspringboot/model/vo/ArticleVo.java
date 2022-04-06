package com.news.newsspringboot.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.news.newsspringboot.model.entity.article.Article;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class ArticleVo {

    private String authorId;

    private String author;

    private String authorPhoto;

    private Article article;
}
