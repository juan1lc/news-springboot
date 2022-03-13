CREATE TABLE post_tag
(
    post_id VARCHAR(32) NOT NULL COMMENT '动态ID',
    tag_id VARCHAR(32) NOT NULL COMMENT '标签ID',
    CONSTRAINT  c_post_id
        FOREIGN KEY (post_id) REFERENCES post (id),
    CONSTRAINT c_tag_id
        FOREIGN KEY (tag_id) REFERENCES tag (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态标签表';