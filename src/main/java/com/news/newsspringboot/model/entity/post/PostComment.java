package com.news.newsspringboot.model.entity.post;

import com.news.newsspringboot.model.entity.CommentBaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "post_comment")
public class PostComment extends CommentBaseEntity {
    private String postid;
}
