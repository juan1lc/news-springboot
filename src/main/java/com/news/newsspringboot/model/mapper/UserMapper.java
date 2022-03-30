package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserVo toUserVo(User user);
}
