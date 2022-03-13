CREATE TABLE article_pic
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '图片ID',
    article_id VARCHAR(32) NOT NULL COMMENT '文章ID',
    picture VARCHAR(255) NOT NULL COMMENT '图片url',
    CONSTRAINT  c_article_pic_id
        FOREIGN KEY (article_id) REFERENCES article (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章图片表';