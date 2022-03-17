package com.news.newsspringboot.model.entity.comment;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "post_comment")
public class PostComment extends CommentBaseEntity{
    private String postid;
}
