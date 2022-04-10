CREATE TABLE article_history
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '浏览历史id',
    articleid VARCHAR(32) NOT NULL COMMENT '文章id',
    userid VARCHAR(32) NOT NULL COMMENT '用户id',
    time timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '浏览时间',
    CONSTRAINT  c_user_article_history_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_article_history_id
        FOREIGN KEY (articleid) REFERENCES article (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章浏览历史表'