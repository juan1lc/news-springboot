CREATE TABLE post_like
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '点赞id',
    post_id VARCHAR(32) NOT NULL COMMENT '动态id',
    user_id VARCHAR(32) NOT NULL COMMENT '点赞者id',
    CONSTRAINT  c_post_like_id
        FOREIGN KEY (post_id) REFERENCES post (id),
    CONSTRAINT  c_user_like_post_id
        FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户点赞动态表';