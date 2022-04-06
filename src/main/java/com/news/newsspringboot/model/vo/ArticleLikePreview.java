package com.news.newsspringboot.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleLikePreview {
    private String id;

    private String authorId;

    private String author;

    private String authorPhoto;

    private String title;

    private String introduction;

    private Date publishTime;

    private String source;

    private Date liketime;
}
