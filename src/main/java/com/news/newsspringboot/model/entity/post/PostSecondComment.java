package com.news.newsspringboot.model.entity.post;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "post_second_comment")
public class PostSecondComment {
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
