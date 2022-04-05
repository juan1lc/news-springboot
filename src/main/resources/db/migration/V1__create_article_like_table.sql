CREATE TABLE article_like
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '点赞id',
    articleid VARCHAR(32) NOT NULL COMMENT '文章id',
    userid VARCHAR(32) NOT NULL COMMENT '喜爱者id',
    username VARCHAR(64) NOT NULL COMMENT '用户名称',
    photo VARCHAR(255) NOT NULL COMMENT '头像',
    article_like int(11) DEFAULT NULL COMMENT '文章点赞数量',
    liketime timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
    CONSTRAINT  c_user_like_article_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_article_like_id
        FOREIGN KEY (articleid) REFERENCES article (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章喜欢表'