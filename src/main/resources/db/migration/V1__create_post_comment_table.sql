CREATE TABLE post_comment
(
    post_id VARCHAR(32) NOT NULL COMMENT '动态id',
    comment_id VARCHAR(32) NOT NULL COMMENT '评论id',
    CONSTRAINT  c_post_comment_id
        FOREIGN KEY (post_id) REFERENCES post (id),
    CONSTRAINT  c_publisher_comment_post_id
        FOREIGN KEY (comment_id) REFERENCES comment (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态评论表';