package com.news.newsspringboot.model.entity.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Article {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    private String id;

    @Column(name = "publish_time")
    private Date publishTime=new Date();

    @Column(name = "article_imgs")
    private String articleImgs;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "type_id")
    private String typeId;

    private Date original_time;

    private Integer article_like=0;

    private String article_like_user;

    private Integer article_status=0;  //默认发布

    private Integer article_browse=0;

    private Integer comment_count=0;

    private String source;

    private String title;

    private String introduction;

    private String content;

    private double w; //相似度

}
