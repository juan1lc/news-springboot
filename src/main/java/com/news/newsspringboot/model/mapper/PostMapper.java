package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.Post;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import com.news.newsspringboot.model.vo.PostVo;
import org.mapstruct.MappingTarget;

public interface PostMapper {
    Post createEntity(PostCreateRequestDto postCreateRequestDto);

    PostVo toVo(Post post);

    Post updateEntity(Post post, PostUpdateRequestDto userUpdateRequestDto);

    PostDetailsVo toDetailsVo(Post post);
}
