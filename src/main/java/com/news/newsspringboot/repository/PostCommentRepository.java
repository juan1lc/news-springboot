package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.post.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, String> {

    List<PostComment> findByPostidOrderByCommentlikeDesc(String id);

}
