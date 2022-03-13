CREATE TABLE follow
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '关注ID',
    user_id VARCHAR(32) NOT NULL COMMENT '关注用户ID',
    follow_id VARCHAR(32) NOT NULL COMMENT '被关注用户ID',
    CONSTRAINT  c_follower_id
        FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT c_followed_id
        FOREIGN KEY (follow_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户关注表';