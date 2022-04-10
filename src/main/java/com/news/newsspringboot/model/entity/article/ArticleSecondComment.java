package com.news.newsspringboot.model.entity.article;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "article_second_comment")
public class ArticleSecondComment {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    private String id;

    private Date createtime=new Date();

    private String content;

    private String userid;

    private String commentparent;

    private String commentid;

    private Integer commentlike=0;
}
