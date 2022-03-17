package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.model.vo.PostCommentVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

public interface PostCommentMapper {

    PostComment postCommentCreateEntity(PostCommentCreateDto postCommentCreateDto);

    PostCommentVo toVo(PostComment postComment);
}
