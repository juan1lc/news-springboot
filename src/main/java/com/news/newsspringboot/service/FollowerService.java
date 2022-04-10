package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.User;

import java.util.List;

public interface FollowerService {

    /// 查找user2是否关注了user1
    boolean checkFollows(String userid1, String userid2);

    /// user2关注/取关user1
    Integer FollowUnfollow(String userid1, String userid2);

    /// 获取关注列表
    List<User> getAllFollows(String userid);

    /// 获取粉丝列表
    List<User> getAllFans(String userid);

    /// 获取关注人数
    Integer followCount(String userid);

    /// 获取粉丝人数
    Integer fansCount(String userid);
}
