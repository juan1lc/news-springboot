package com.news.newsspringboot.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class CommentBaseEntity {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    private String id;

    private Date createtime;

    private String content;

    private String userid;

    private String commentparent;

    private Integer commentcount;

    private Integer commentlike;

    private String commentlikeuser;

}
