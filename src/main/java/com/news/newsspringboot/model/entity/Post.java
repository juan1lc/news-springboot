package com.news.newsspringboot.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    private String id;

    private Date publish_time;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="publisher_id",referencedColumnName = "id")
    private User user;

    private String content;

    private boolean isrepost;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_comment", joinColumns = @JoinColumn(name="post_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="comment_id", referencedColumnName = "id"))
    private List<Comment> post_comment;
}
