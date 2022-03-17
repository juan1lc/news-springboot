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

   List<CommentReply> commentReplyList;

   @Data
   private static class CommentReply{
        private Integer commentId;
        private String commentContent;
        private Integer commentLike;
        private String commentLikeUsers;
       @JsonFormat
        private LocalDateTime commentCreateTime;
        private Integer commentAuthorId;
        private String commentAuthorAvatar;
        private String commentAuthorName;
        private String commentAuthorPhone;
        private Integer parentCommentId;
        private String parentCommentContent;
        private Integer parentCommentLike;
       @JsonFormat
        private LocalDateTime parentCommentCreateTime;
        private Integer parentCommentAuthorId;
        private String parentCommentAuthorAvatar;
        private String parentCommentAuthorName;
        private String parentCommentAuthorPhone;
    }


}
