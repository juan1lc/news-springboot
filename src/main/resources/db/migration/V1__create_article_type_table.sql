CREATE TABLE article_type
(
    article_id VARCHAR(32) NOT NULL COMMENT '文章ID',
    type_id VARCHAR(32) NOT NULL COMMENT '类型ID',
    CONSTRAINT  c_article_id
        FOREIGN KEY (article_id) REFERENCES article (id),
    CONSTRAINT c_type_id
        FOREIGN KEY (type_id) REFERENCES type (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章类型表';