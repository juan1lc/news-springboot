package com.news.newsspringboot.service;

public interface CommentLikeService {

    boolean CheckALikeTable(String commentid, String userid);

    Integer LikeDisLikeAComment(String commentid, String userid);

    Integer ACountLikeNum(String commentid);

    boolean CheckPLikeTable(String commentid, String userid);

    Integer LikeDisLikePComment(String commentid, String userid);

    Integer PCountLikeNum(String commentid);
}
