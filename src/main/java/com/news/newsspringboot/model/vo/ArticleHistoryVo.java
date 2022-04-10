package com.news.newsspringboot.model.vo;

import com.news.newsspringboot.model.entity.article.ArticleHistory;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleHistoryVo {

    private ArticlePreview articlePreview;

    private Date time;
}
