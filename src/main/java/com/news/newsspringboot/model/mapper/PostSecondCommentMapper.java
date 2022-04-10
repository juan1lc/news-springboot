package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.entity.post.PostSecondComment;
import com.news.newsspringboot.model.vo.SecondCommentVo;

public interface PostSecondCommentMapper {
    SecondCommentVo toVo(PostSecondComment postSecondComment);
}
