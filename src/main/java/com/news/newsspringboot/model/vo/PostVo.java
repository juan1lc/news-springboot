package com.news.newsspringboot.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.news.newsspringboot.model.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 动态Vo模型
 */
@Data
public class PostVo {

    private String authorPhoto;

    private String author;
    
    private Post post;
    
}
