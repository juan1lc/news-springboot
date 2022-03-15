package com.news.newsspringboot.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    private String id;

    private Date publish_time;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="publisher_id",referencedColumnName = "id")
    private User user;

    private Date original_time;

    private String source;

    private String title;

    private String introduction;

    private String content;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_comment", joinColumns = @JoinColumn(name="article_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="comment_id", referencedColumnName = "id"))
    private Set<Comment> article_comment;

}
