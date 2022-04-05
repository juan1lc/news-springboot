package com.news.newsspringboot.model.entity.comment;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "article_comment")
public class ArticleComment extends CommentBaseEntity {
    private String articleid;
}
