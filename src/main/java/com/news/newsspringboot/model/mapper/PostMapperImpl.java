package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.Post;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.model.vo.PostCommentVo;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import com.news.newsspringboot.model.vo.PostVo;
import com.news.newsspringboot.repository.PostCommentRepository;
import com.news.newsspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.AbstractList;
import java.util.Date;
import java.util.List;

@Component
public class PostMapperImpl implements PostMapper{
    UserService userService;

    PostCommentRepository postCommentRepository;

    PostCommentMapper postCommentMapper;

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
        postVo.setContent( post.getContent() );
        postVo.setPostImgs( post.getPostImgs() );
        postVo.setPostLike( post.getPostLike() );
        postVo.setPostBrowse( post.getPostBrowse() );
        postVo.setCommentCount( post.getCommentCount() );
        if ( post.getCreateTime() != null ) {
            postVo.setCreateTime( LocalDateTime.ofInstant( post.getCreateTime().toInstant(), ZoneId.of( "UTC" ) ) );
        }

        return postVo;
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
        String postId = post.getId();
        List<PostCommentVo> postCommentList = postCommentRepository.findByPostid(postId).stream().map(postCommentMapper::toVo).toList();


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
        postDetailsVo.setPostCommentList(postCommentList);

        return postDetailsVo;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPostCommentRepository(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    @Autowired
    public void setPostCommentMapper(PostCommentMapper postCommentMapper) {
        this.postCommentMapper = postCommentMapper;
    }
}
