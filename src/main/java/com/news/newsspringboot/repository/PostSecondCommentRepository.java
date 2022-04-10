package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.post.PostSecondComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostSecondCommentRepository extends JpaRepository<PostSecondComment, String> {

    List<PostSecondComment> findByCommentidOrderByCommentlikeDesc(String commentid);
}
