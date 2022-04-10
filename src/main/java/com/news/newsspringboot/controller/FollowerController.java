package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
public class FollowerController {

    FollowerService followerService;

    //获取某用户的关注列表
    @GetMapping(value = "/all-follows/{userid}", produces = "application/json;charset=utf-8")
    List<User> getAllFollows(@PathVariable(value = "userid") String userid){
        return followerService.getAllFollows(userid);
    }

    //获取某用户的粉丝列表
    @GetMapping(value = "/all-fans/{userid}", produces = "application/json;charset=utf-8")
    List<User> getAllFans(@PathVariable(value = "userid") String userid){
        return followerService.getAllFans(userid);
    }

    //获取某用户的关注人数
    @GetMapping(value = "/follow-count/{userid}", produces = "application/json;charset=utf-8")
    Integer getFollowCount(@PathVariable(value = "userid") String userid){
        return followerService.followCount(userid);
    }

    //获取某用户的粉丝人数
    @GetMapping(value = "/fans-count/{userid}", produces = "application/json;charset=utf-8")
    Integer getFansCount(@PathVariable(value = "userid") String userid){
        return followerService.fansCount(userid);
    }

    @PostMapping("/{userid1}/{userid2}")
    Integer followUnfollow(@PathVariable(value = "userid1") String userid1,
                           @PathVariable(value = "userid2") String userid2){
        return followerService.FollowUnfollow(userid1, userid2);
    }

    @GetMapping("/{userid1}/{userid2}")
    boolean checkFollows(@PathVariable(value = "userid1") String userid1,
                          @PathVariable(value = "userid2") String userid2){
        return followerService.checkFollows(userid1, userid2);
    }

    @Autowired
    public void setFollowerService(FollowerService followerService) {
        this.followerService = followerService;
    }
}
