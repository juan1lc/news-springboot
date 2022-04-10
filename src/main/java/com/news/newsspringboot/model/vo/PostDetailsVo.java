package com.news.newsspringboot.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 动态详情VO
 */
@Data
public class PostDetailsVo {

    private String authorPhoto;

    private String author;

    private String content;

    private String postImgs;

    private String postTags;

    private Integer postLike;

    private String postLikeUsers;

    private Integer postBrowse;

    private Integer commentCount;

    @JsonFormat
    private LocalDateTime createTime;

    private  List<PostCommentVo> PostCommentList;


}
