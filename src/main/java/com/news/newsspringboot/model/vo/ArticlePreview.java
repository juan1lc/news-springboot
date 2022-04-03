package com.news.newsspringboot.model.vo;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.util.Date;

@Data
public class ArticlePreview {

    private String id;

    private String authorId;

    private String author;

    private String authorPhoto;

    private String title;

    private String introduction;

    private Date publishTime;

    private String source;
}
