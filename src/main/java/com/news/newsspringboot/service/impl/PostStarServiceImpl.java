package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.post.PostStar;
import com.news.newsspringboot.repository.PostStarRepository;
import com.news.newsspringboot.service.PostStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostStarServiceImpl implements PostStarService {
    PostStarRepository postStarRepository;

    @Override
    public boolean checkStarTable(String postid, String userid) {
        Optional<PostStar> stared = postStarRepository.findByPostidAndUserid(postid, userid);
        return stared.isPresent();
    }

    @Override
    public Integer StarUnstar(String postid, String userid) {
        boolean isStared = checkStarTable(postid, userid);
        if(isStared){
            PostStar postStar = postStarRepository.getPostStarByPostidAndUserid(postid, userid);
            postStarRepository.delete(postStar);
            return 0;
        } else{
            starPost(postid, userid);
        }
        return 1;
    }

    private PostStar starPost(String postid, String userid) {
        PostStar postStar = new PostStar();
        postStar.setPostid(postid);
        postStar.setUserid(userid);

        return postStarRepository.save(postStar);
    }

    @Autowired
    public void setPostStarRepository(PostStarRepository postStarRepository) {
        this.postStarRepository = postStarRepository;
    }
}
