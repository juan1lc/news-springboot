CREATE TABLE post_comment
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '评论id',
    post_id VARCHAR(32) NOT NULL COMMENT '文章url',
    publisher_id VARCHAR(32) NOT NULL COMMENT '评论者id',
    content tinytext NOT NULL COMMENT '评论内容',
    time DATETIME NOT NULL COMMENT '评论时间',
    CONSTRAINT  c_post_comment_id
        FOREIGN KEY (post_id) REFERENCES article (id),
    CONSTRAINT  c_publisher_commentpost_id
        FOREIGN KEY (publisher_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态评论表';