package com.news.newsspringboot.model.mapper.impl;

import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.post.PostComment;
import com.news.newsspringboot.model.mapper.PostCommentMapper;
import com.news.newsspringboot.model.vo.PostCommentVo;
import com.news.newsspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostCommentMapperImpl implements PostCommentMapper {
    UserService userService;

    @Override
    public PostComment postCommentCreateEntity(PostCommentCreateDto postCommentCreateDto) {
        if ( postCommentCreateDto == null ) {
            return null;
        }

        PostComment postComment = new PostComment();

        postComment.setCreatetime( postCommentCreateDto.getCreatetime() );
        postComment.setContent( postCommentCreateDto.getContent() );
        postComment.setUserid( postCommentCreateDto.getUserid() );
        postComment.setCommentparent( postCommentCreateDto.getCommentparent() );
        postComment.setCommentlike( postCommentCreateDto.getCommentlike() );
        postComment.setCommentlikeuser( postCommentCreateDto.getCommentlikeuser() );
        postComment.setPostid(postCommentCreateDto.getPostid());
        postComment.setCommentcount(postCommentCreateDto.getCommentcount());

        return postComment;
    }

    @Override
    public PostCommentVo toVo(PostComment postComment) {
        if ( postComment == null ) {
            return null;
        }

        String userId = postComment.getUserid();
        User user = userService.getUserById(userId);

        PostCommentVo postCommentVo = new PostCommentVo();

        postCommentVo.setAuthor(user.getUsername());
        postCommentVo.setAuthorPhoto(user.getPhoto());
        postCommentVo.setCommentlike(postComment.getCommentlike());
        postCommentVo.setCommentparent(postComment.getCommentparent());
        postCommentVo.setContent(postComment.getContent());
        postCommentVo.setCreatetime(postComment.getCreatetime());
        postCommentVo.setUserid(postComment.getUserid());
        postCommentVo.setId(postComment.getId());
        postCommentVo.setCommentcount(postComment.getCommentcount());

        return postCommentVo;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
