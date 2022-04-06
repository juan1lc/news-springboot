package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.post.PostLike;

public interface PostLikeService {
    PostLike likePost(String userid, String postid);
}
