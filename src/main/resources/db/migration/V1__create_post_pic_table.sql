CREATE TABLE post_pic
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '图片ID',
    post_id VARCHAR(32) NOT NULL COMMENT '动态ID',
    picture VARCHAR(255) NOT NULL COMMENT '图片url',
    CONSTRAINT  c_post_pic_id
        FOREIGN KEY (post_id) REFERENCES article (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态图片表';