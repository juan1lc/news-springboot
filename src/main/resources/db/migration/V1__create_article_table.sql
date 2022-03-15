CREATE TABLE article
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '文章ID',
    publish_time DATETIME NOT NULL COMMENT '发布时间',
    article_imgs varchar(255) DEFAULT NULL COMMENT '文章的图片url（多个图片之间使用“，”分隔）',
    user_id VARCHAR(32) NOT NULL COMMENT '发布用户id',
    author VARCHAR(64) NOT NULL COMMENT '发布用户名称',
    content LONGTEXT NOT NULL COMMENT '文章内容',
    type_id VARCHAR(32) DEFAULT NULL COMMENT '文章类型',
    tags VARCHAR(255) DEFAULT NULL COMMENT '文章标签 用“,”分割',
    article_like int(11) DEFAULT NULL COMMENT '文章点赞数量',
    article_like_user varchar(255) DEFAULT NULL COMMENT '点赞用户名（多个用户之间使用“，”分割）',
    article_browse int(11) DEFAULT NULL COMMENT '文章浏览量',
    comment_count int(11) DEFAULT NULL COMMENT '文章评论总数',
    article_status int(11) NOT NULL COMMENT '文章发布的状态（0：发布，1：草稿，2：删除）',
    original_time DATETIME NOT NULL COMMENT '原文章发布时间',
    source VARCHAR(255) NULL COMMENT '文章来源url',
    title VARCHAR(64) NOT NULL COMMENT '标题',
    introduction VARCHAR(255) NOT NULL COMMENT '导语',

    CONSTRAINT article_fk_user
    FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT article_fk_type
    FOREIGN KEY (type_id) REFERENCES type (id),
    FULLTEXT KEY article_full_index (content,type_id,author)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章表';