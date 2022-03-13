CREATE TABLE post
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '动态ID',
    publish_time DATETIME NOT NULL COMMENT '发布时间',
    publisher_id VARCHAR(32) NOT NULL COMMENT '发布用户id',
    content TEXT NOT NULL COMMENT '动态内容',
    isrepost boolean NOT NULL COMMENT '是否是转载',
    CONSTRAINT c_post_publisher_id
        FOREIGN KEY (publisher_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态表';