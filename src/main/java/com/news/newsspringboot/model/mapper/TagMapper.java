package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.dto.TagCreateDto;
import com.news.newsspringboot.model.entity.Tag;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TagMapper {
    Tag toTag(TagCreateDto tagCreateDto);
}
