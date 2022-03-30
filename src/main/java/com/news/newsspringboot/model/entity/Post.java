package com.news.newsspringboot.model.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    private String id;

    //多张图片之间用“,”分割
    @Column(name = "post_imgs")
    private String postImgs;

    @NotBlank
    private String userid;

    private String content;

    private boolean isrepost;

    //多个标签之间用“-”分割
    private String tags;

    @Column(name = "post_like")
    private Integer postLike;

    //记录点赞的用户名，多用户之间用“，分割”
    @Column(name = "post_like_user")
    private String postLikeUser;

    @Column(name = "post_browse")
    private Integer postBrowse;

    @Column(name = "comment_count")
    private Integer commentCount;

     //动态状态（0：发布，1：草稿，2：删除）
    private Integer poststatus;

    private String location;

    @Column (name = "create_time")
    private Date createTime;

    @Column (name = "update_time")
    private Date updateTime;

}
