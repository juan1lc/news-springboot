CREATE TABLE post_history
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '浏览历史id',
    postid VARCHAR(32) NOT NULL COMMENT '文章id',
    userid VARCHAR(32) NOT NULL COMMENT '用户id',
    time timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '浏览时间',
    CONSTRAINT  c_user_post_history_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_post_history_id
        FOREIGN KEY (postid) REFERENCES post (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态浏览历史表'