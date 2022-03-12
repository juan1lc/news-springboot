package com.news.newsspringboot.mapper;

import com.news.newsspringboot.dto.UserUpdateRequest;
import com.news.newsspringboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
