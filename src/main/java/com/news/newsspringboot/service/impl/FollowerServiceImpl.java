package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.Follower;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.repository.FollowerRepository;
import com.news.newsspringboot.repository.UserRepository;
import com.news.newsspringboot.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowerServiceImpl implements FollowerService {
    FollowerRepository followerRepository;
    UserRepository userRepository;

    @Override
    public boolean checkFollows(String userid1, String userid2) {
        Optional<Follower> f = followerRepository.findByUserid1AndUserid2(userid1, userid2);
        return f.isPresent();
    }

    @Override
    public Integer FollowUnfollow(String userid1, String userid2) {
        if(checkFollows(userid1, userid2)){//已关注，取关操作
            Follower f = followerRepository.getFollowerByUserid1AndUserid2(userid1, userid2);
            followerRepository.delete(f);
            return 0;
        }else{//未关注，执行关注操作
            Follower f = new Follower();
            f.setUserid1(userid1);
            f.setUserid2(userid2);
            followerRepository.save(f);
        }
        return 1;
    }

    @Override
    public List<User> getAllFollows(String userid) {
        List<Follower> followers = followerRepository.findAllByUserid2(userid);
        List<User> users = new ArrayList<>();

        for(Follower follower:followers){
            String userid1 = follower.getUserid1();
            User user1 = userRepository.getById(userid1);
            users.add(user1);
        }
        return users;
    }

    @Override
    public List<User> getAllFans(String userid) {
        List<Follower> followers = followerRepository.findAllByUserid1(userid);
        List<User> users = new ArrayList<>();
        for(Follower follower:followers){
            String userid2 = follower.getUserid2();
            User user2 = userRepository.getById(userid2);
            users.add(user2);
        }
        return users;
    }

    @Override
    public Integer followCount(String userid) {
        List<Follower> followers = followerRepository.findAllByUserid2(userid);
        return followers.size();
    }

    @Override
    public Integer fansCount(String userid) {
        List<Follower> followers = followerRepository.findAllByUserid1(userid);
        return followers.size();
    }


    @Autowired
    public void setFollowerRepository(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
