CREATE TABLE article_comment_like
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '点赞id',
    commentid VARCHAR(32) NOT NULL COMMENT '文章id',
    userid VARCHAR(32) NOT NULL COMMENT '喜爱者id',
    CONSTRAINT  c_user_like_article_comment_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_article_comment_like_id
        FOREIGN KEY (commentid) REFERENCES article_comment (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章喜欢表'