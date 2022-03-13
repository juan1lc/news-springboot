CREATE TABLE article
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '文章ID',
    publish_time DATETIME NOT NULL COMMENT '发布时间',
    publisher_id VARCHAR(32) NOT NULL COMMENT '发布用户id',
    original_time DATETIME NOT NULL COMMENT '原文章发布时间',
    source VARCHAR(255) NULL COMMENT '文章来源url',
    title VARCHAR(64) NOT NULL COMMENT '标题',
    introduction VARCHAR(255) NOT NULL COMMENT '导语',
    content LONGTEXT NOT NULL COMMENT '文章内容',
    CONSTRAINT c_publisher_id
    FOREIGN KEY (publisher_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章表';