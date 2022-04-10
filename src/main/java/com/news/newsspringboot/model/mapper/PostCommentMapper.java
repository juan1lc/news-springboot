package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.entity.post.PostComment;
import com.news.newsspringboot.model.vo.PostCommentVo;

public interface PostCommentMapper {

    PostComment postCommentCreateEntity(PostCommentCreateDto postCommentCreateDto);

    PostCommentVo toVo(PostComment postComment);
}
