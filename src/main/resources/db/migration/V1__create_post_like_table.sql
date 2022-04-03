CREATE TABLE post_like
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '点赞id',
    postid VARCHAR(32) NOT NULL COMMENT '动态id',
    userid VARCHAR(32) NOT NULL COMMENT '喜爱者id',
    username VARCHAR(64) NOT NULL COMMENT '用户名称',
    photo VARCHAR(255) NOT NULL COMMENT '头像',
    liketime timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
    CONSTRAINT  c_user_like_post_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_post_like_id
        FOREIGN KEY (postid) REFERENCES post (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态喜欢表'