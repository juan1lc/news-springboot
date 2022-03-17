package com.news.newsspringboot.model.entity.comment;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class ArticleComment extends CommentBaseEntity {
    private String articleid;
}
