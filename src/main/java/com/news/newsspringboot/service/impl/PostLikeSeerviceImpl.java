package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.PostLike;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.repository.PostLikeRepository;
import com.news.newsspringboot.service.PostLikeService;
import com.news.newsspringboot.service.PostService;
import com.news.newsspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeSeerviceImpl implements PostLikeService {
    PostLikeRepository postLikeRepository;
    UserService userService;

    @Override
    public PostLike likePost(String userid, String postid) {
        User user = userService.getUserById(userid);
        String username = user.getUsername();
        String avatar = user.getPhoto();
        PostLike likePost = new PostLike();
        likePost.setPostid(postid);
        likePost.setUserid(userid);
        likePost.setUsername(username);
        likePost.setPhoto(avatar);

        return postLikeRepository.save(likePost);
    }

    @Autowired
    public void setPostLikeRepository(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
