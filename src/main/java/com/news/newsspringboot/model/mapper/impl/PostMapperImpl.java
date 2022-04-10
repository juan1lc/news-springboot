package com.news.newsspringboot.model.mapper.impl;

import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.post.Post;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.post.PostHistory;
import com.news.newsspringboot.model.mapper.PostCommentMapper;
import com.news.newsspringboot.model.mapper.PostMapper;
import com.news.newsspringboot.model.vo.*;
import com.news.newsspringboot.repository.PostCommentRepository;
import com.news.newsspringboot.repository.PostRepository;
import com.news.newsspringboot.repository.UserRepository;
import com.news.newsspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class PostMapperImpl implements PostMapper {
    UserService userService;

    UserRepository userRepository;

    PostCommentRepository postCommentRepository;

    PostCommentMapper postCommentMapper;

    PostRepository postRepository;

    @Override
    public Post createEntity(PostCreateRequestDto postCreateRequestDto) {
        if ( postCreateRequestDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setPostImgs( postCreateRequestDto.getPostImgs() );
        post.setUserid( postCreateRequestDto.getUserId() );
        post.setContent( postCreateRequestDto.getContent() );
        post.setIsrepost( postCreateRequestDto.isIsrepost() );
        post.setTags( postCreateRequestDto.getTags() );
        post.setPostLike( postCreateRequestDto.getPostLike() );
        post.setPostBrowse( postCreateRequestDto.getPostBrowse() );
        post.setCommentCount( postCreateRequestDto.getCommentCount() );
        post.setPoststatus( postCreateRequestDto.getPostStatus() );
        post.setLocation( postCreateRequestDto.getLocation() );
        post.setCreateTime( postCreateRequestDto.getCreateTime() );
        post.setUpdateTime( postCreateRequestDto.getUpdateTime() );

        return post;
    }


    @Override
    public PostVo toVo(Post post) {
        if ( post == null ) {
            return null;
        }

        String userId = post.getUserid();
        User user = userService.getUserById(userId);

        PostVo postVo = new PostVo();
        postVo.setAuthor(user.getUsername());
        postVo.setAuthorPhoto(user.getPhoto());
        postVo.setPost(post);

        return postVo;
    }

    public PostLikePreview toLikePreview(Post post, Date liketime){
        if ( post == null ) {
            return null;
        }

        String userId = post.getUserid();
        User user = userService.getUserById(userId);

        PostLikePreview postVo = new PostLikePreview();
        postVo.setAuthor(user.getUsername());
        postVo.setAuthorPhoto(user.getPhoto());
        postVo.setPost(post);
        postVo.setLiketime(liketime);

        return postVo;
    }

    @Override
    public PostHistoryVo toHistoryVo(PostHistory postHistory) {
        if ( postHistory == null ) {
            return null;
        }
        String userId = postHistory.getUserid();
        String postId = postHistory.getPostid();

        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);

        PostHistoryVo postHistoryVo = new PostHistoryVo();
        postHistoryVo.setPost(post);
        postHistoryVo.setTime(postHistory.getTime());
        postHistoryVo.setAuthor(user.getUsername());
        postHistoryVo.setAuthorPhoto(user.getPhoto());

        return postHistoryVo;
    }

    @Override
    public Post updateEntity(Post post, PostUpdateRequestDto postUpdateRequestDto) {
        if ( postUpdateRequestDto == null ) {
            return null;
        }

        if ( postUpdateRequestDto.getPostImgs()!=null) {
            post.setPostImgs(postUpdateRequestDto.getPostImgs());
        }
        post.setContent(postUpdateRequestDto.getContent());
        if ( postUpdateRequestDto.getTags()!=null) {
            post.setTags(postUpdateRequestDto.getTags());
        }
        if ( postUpdateRequestDto.getPoststatus()!=null) {
            post.setPoststatus(postUpdateRequestDto.getPoststatus());
        }
        if ( postUpdateRequestDto.getLocation()!=null) {
            post.setLocation(postUpdateRequestDto.getLocation());
        }
        post.setUpdateTime(new Date());

        return post;
    }

    @Override
    public PostDetailsVo toDetailsVo(Post post) {
        if ( post == null ) {
            return null;
        }

        String userId = post.getUserid();
        User user = userService.getUserById(userId);


        PostDetailsVo postDetailsVo = new PostDetailsVo();
        postDetailsVo.setAuthor(user.getUsername());
        postDetailsVo.setAuthorPhoto(user.getPhoto());
        postDetailsVo.setContent( post.getContent() );
        postDetailsVo.setPostImgs( post.getPostImgs() );
        postDetailsVo.setPostTags(post.getTags());
        postDetailsVo.setPostLike( post.getPostLike() );
        postDetailsVo.setPostLikeUsers(post.getPostLikeUser());
        postDetailsVo.setPostBrowse( post.getPostBrowse() );
        postDetailsVo.setCommentCount( post.getCommentCount() );
        if ( post.getCreateTime() != null ) {
            postDetailsVo.setCreateTime( LocalDateTime.ofInstant( post.getCreateTime().toInstant(), ZoneId.of( "UTC" ) ) );
        }

        return postDetailsVo;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPostCommentRepository(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    @Autowired
    public void setPostCommentMapper(PostCommentMapper postCommentMapper) {
        this.postCommentMapper = postCommentMapper;
    }

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
