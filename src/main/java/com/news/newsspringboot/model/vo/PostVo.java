package com.news.newsspringboot.model.vo;

import com.news.newsspringboot.model.entity.post.Post;
import lombok.Data;

/**
 * 动态Vo模型
 */
@Data
public class PostVo {

    private String authorPhoto;

    private String author;
    
    private Post post;
    
}
