CREATE TABLE post_star
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '收藏id',
    postid VARCHAR(32) NOT NULL COMMENT '动态id',
    userid VARCHAR(32) NOT NULL COMMENT '收藏者id',
    liketime timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
    CONSTRAINT  c_user_star_post_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_post_star_id
        FOREIGN KEY (postid) REFERENCES post (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态收藏表'