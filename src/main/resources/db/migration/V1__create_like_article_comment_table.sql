CREATE TABLE like_article_comment
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '点赞id',
    comment_id VARCHAR(32) NOT NULL COMMENT '评论id',
    user_id VARCHAR(32) NOT NULL COMMENT '点赞者id',
    CONSTRAINT  c_article_comment_like_id
        FOREIGN KEY (comment_id) REFERENCES article_comment (id),
    CONSTRAINT  c_user_like_article_comment_id
        FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章评论点赞表';