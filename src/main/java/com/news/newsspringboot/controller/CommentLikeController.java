package com.news.newsspringboot.controller;

import com.news.newsspringboot.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like-comment")
public class CommentLikeController {

    CommentLikeService commentLikeService;

    @GetMapping("/article/count/{comment-id}")
    Integer CommentLikeCount (@PathVariable(value = "comment-id") String commentId){
        return commentLikeService.ACountLikeNum(commentId);
    }
    @GetMapping("/post/count/{comment-id}")
    Integer PostCommentLikeCount (@PathVariable(value = "comment-id") String commentId){
        return commentLikeService.PCountLikeNum(commentId);
    }


    @GetMapping("/article/{comment-id}/{user-id}")
    boolean likedComment(@PathVariable(value = "comment-id") String commentid,
                         @PathVariable(value = "user-id") String userid){
        return commentLikeService.CheckALikeTable(commentid, userid);
    }
    @GetMapping("/post/{comment-id}/{user-id}")
    boolean likedPostComment(@PathVariable(value = "comment-id") String commentid,
                         @PathVariable(value = "user-id") String userid){
        return commentLikeService.CheckPLikeTable(commentid, userid);
    }


    @PostMapping("/article/{comment-id}/{user-id}")
    Integer likeUnlikeComment(@PathVariable(value = "comment-id") String commentid,
                              @PathVariable(value = "user-id") String userid){
        return commentLikeService.LikeDisLikeAComment(commentid, userid);
    }
    @PostMapping("/post/{comment-id}/{user-id}")
    Integer likeUnlikePostComment(@PathVariable(value = "comment-id") String commentid,
                              @PathVariable(value = "user-id") String userid){
        return commentLikeService.LikeDisLikePComment(commentid, userid);
    }


    @Autowired
    public void setCommentLikeService(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }
}
