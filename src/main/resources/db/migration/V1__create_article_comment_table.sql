CREATE TABLE article_comment
(
    article_id VARCHAR(32) NOT NULL COMMENT '文章id',
    comment_id VARCHAR(32) NOT NULL COMMENT '评论id',
    CONSTRAINT  c_article_comment_id
        FOREIGN KEY (article_id) REFERENCES article (id),
    CONSTRAINT  c_publisher_comment_article_type
        FOREIGN KEY (comment_id) REFERENCES comment (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章评论表';