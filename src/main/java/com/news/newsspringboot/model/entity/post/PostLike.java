package com.news.newsspringboot.model.entity.post;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "post_like")
public class PostLike {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    private String id;

    private String postid;

    private String userid;

    private String username;

    private String photo;

    private Date liketime=new Date();
}
