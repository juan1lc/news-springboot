package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.post.PostCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostCommentLikeRepository extends JpaRepository<PostCommentLike, String> {

    Optional<PostCommentLike> findByUseridAndCommentid(String userid, String commentid);

    PostCommentLike getPostCommentLikeByCommentidAndUserid(String postid, String userid);

    List<PostCommentLike> findAllByCommentid(String commentid);
}
