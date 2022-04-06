package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.post.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void getPostByUserIdAndPostStatus(){
        String userId = "26SUAsqbKM2FBddUX4k2OJl1aTZ";
        Integer postStatus = 1;
        Pageable pageable = null;
        Page<Post> postPage = postRepository.findByUseridAndPoststatus(userId, postStatus, pageable);

        long num = postPage.getTotalElements();
        System.out.println(num);
    }
}
