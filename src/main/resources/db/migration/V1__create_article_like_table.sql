CREATE TABLE article_like
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '点赞id',
    article_id VARCHAR(32) NOT NULL COMMENT '文章id',
    user_id VARCHAR(32) NOT NULL COMMENT '点赞者id',
    CONSTRAINT  c_article_like_id
        FOREIGN KEY (article_id) REFERENCES article (id),
    CONSTRAINT  c_user_like_id
        FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户点赞表';