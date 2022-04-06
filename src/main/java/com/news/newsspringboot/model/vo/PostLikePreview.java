package com.news.newsspringboot.model.vo;

import com.news.newsspringboot.model.entity.post.Post;
import lombok.Data;

import java.util.Date;

@Data
public class PostLikePreview {
    private String authorPhoto;

    private String author;

    private Post post;

    private Date liketime;
}
