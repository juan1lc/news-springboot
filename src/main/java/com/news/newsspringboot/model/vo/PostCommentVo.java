package com.news.newsspringboot.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PostCommentVo {

    private String id;

    private Date createtime;

    private String content;

    private String userid;

    private String commentparent;

    private Integer commentlike;

    private String author;

    private String authorPhoto;

    private Integer commentcount;
}
