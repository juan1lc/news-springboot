package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.post.Post;
import com.news.newsspringboot.model.entity.post.PostHistory;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import com.news.newsspringboot.model.vo.PostHistoryVo;
import com.news.newsspringboot.model.vo.PostLikePreview;
import com.news.newsspringboot.model.vo.PostVo;

import java.util.Date;

public interface PostMapper {
    Post createEntity(PostCreateRequestDto postCreateRequestDto);

    PostVo toVo(Post post);

    Post updateEntity(Post post, PostUpdateRequestDto userUpdateRequestDto);

    PostDetailsVo toDetailsVo(Post post);

    PostLikePreview toLikePreview(Post post, Date liketime);

    PostHistoryVo toHistoryVo(PostHistory postHistory);
}
