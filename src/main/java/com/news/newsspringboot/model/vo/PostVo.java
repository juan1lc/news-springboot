package com.news.newsspringboot.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 动态Vo模型
 */
@Data
public class PostVo {

    private String authorPhoto;

    private String author;

    private String content;

    private String postImgs;

    private String postTags;

    private Integer postLike;

    private String postLikeUsers;

    private Integer postBrowse;

    private Integer commentCount;

    private String location;

    @JsonFormat
    private LocalDateTime createTime;
    
    
    
}
