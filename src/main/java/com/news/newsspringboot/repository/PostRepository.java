package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {


    List<Post> findByContentLike(String content);

    @Query(value="select * from Post p where p.userid=?1 and  p.poststatus=?2", nativeQuery=true)
    Page<Post> findByUseridAndPoststatus(String userId, Integer poststatus, Pageable pageable);

}