CREATE TABLE article_star
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '收藏id',
    articleid VARCHAR(32) NOT NULL COMMENT '文章id',
    userid VARCHAR(32) NOT NULL COMMENT '收藏者id',
    liketime timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
    CONSTRAINT  c_user_star_article_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_article_star_id
        FOREIGN KEY (articleid) REFERENCES article (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章收藏表'