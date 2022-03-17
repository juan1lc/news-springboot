CREATE TABLE post
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '动态ID',
    post_imgs varchar(255) DEFAULT NULL COMMENT '动态的图片url（多个图片之间使用“，”分隔）',
    userid VARCHAR(32) NOT NULL COMMENT '发布用户id',
    content TEXT NOT NULL COMMENT '动态内容',
    isrepost boolean NOT NULL COMMENT '是否是转载',
    tags VARCHAR(255) DEFAULT NULL COMMENT '动态标签 用“-”分割',
    post_like int(11) DEFAULT NULL COMMENT '动态点赞数量',
    post_like_user varchar(255) DEFAULT NULL COMMENT '点赞用户名（多个用户之间使用“，”分割）',
    post_browse int(11) DEFAULT NULL COMMENT '动态浏览量',
    comment_count int(11) DEFAULT NULL COMMENT '动态评论总数',
    poststatus int(11) NOT NULL COMMENT '动态发布的状态（0：发布，1：草稿，2：删除）',
    location varchar(255) DEFAULT NULL COMMENT '发布动态的定位位置（经纬度或者地址字符串）',
    create_time timestamp NOT NULL COMMENT '动态创建时间',
    update_time timestamp NOT NULL COMMENT '动态更新时间',
    CONSTRAINT post_fk_user
        FOREIGN KEY (userid) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态表';