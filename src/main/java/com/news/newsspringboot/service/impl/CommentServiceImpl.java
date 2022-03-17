package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.exception.BizException;
import com.news.newsspringboot.exception.ExceptionType;
import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.entity.Post;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.model.mapper.PostCommentMapper;
import com.news.newsspringboot.repository.PostCommentRepository;
import com.news.newsspringboot.repository.PostRepository;
import com.news.newsspringboot.service.CommentService;
import com.news.newsspringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    PostCommentRepository repository;

    PostCommentMapper postCommentMapper;

    PostService postService;

    PostRepository postRepository;

    @Override
    public PostComment create(PostCommentCreateDto postCommentCreateDto, String postId) {
        PostComment postComment = postCommentMapper.postCommentCreateEntity(postCommentCreateDto);
        postComment.setPostid(postId);
        Post post = postRepository.getById(postId);
        //草稿箱中的动态不能被评论
        checkPostStatus(post.getPoststatus());
        post.setCommentCount(post.getCommentCount()+1);
        postRepository.save(post);
        return repository.save(postComment);
    }

    private void checkPostStatus(Integer poststatus) {
        if(poststatus!=0){
            throw new BizException(ExceptionType.POST_IS_A_DRAFT);
        }
    }

    @Override
    public void delete(String commentId) {
        PostComment postComment = repository.getById(commentId);
        String postId = postComment.getPostid();
        Post post = postRepository.getById(postId);
        post.setCommentCount(post.getCommentCount()-1);
        postRepository.save(post);
        repository.delete(postComment);
    }

    @Autowired
    public void setRepository(PostCommentRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setPostCommentMapper(PostCommentMapper postCommentMapper) {
        this.postCommentMapper = postCommentMapper;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
