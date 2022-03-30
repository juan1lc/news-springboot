package com.news.newsspringboot.model.dto;

import cn.hutool.core.date.DateTime;
import com.news.newsspringboot.model.entity.User;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class PostCreateRequestDto {

    @Column(name = "post_imgs")
    private String postImgs;

    @NotBlank
    @Column(name = "userid")
    private String userId;

    @NotBlank
    private String content;

    private boolean isrepost=false;

    private String tags;

    @Column(name = "post_like")
    private Integer postLike=0;

    @Column(name = "post_browse")
    private Integer postBrowse=0;

    @Column(name = "comment_count")
    private Integer commentCount=0;

    //动态状态（0：发布，1：草稿）
    private Integer postStatus=0;

    //之后连接服务器获取ip，现在先默认广东广州
    private String location = "广东广州";

    @Column (name = "create_time")
    private Date createTime = new Date();

    @Column (name = "update_time")
    private Date updateTime = new Date();
}
