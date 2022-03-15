CREATE TABLE article_comment
(
    comment_id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '评论id',
    article_id VARCHAR(32) NOT NULL COMMENT '评论对应文章的ID',
    user_id VARCHAR(32) NOT NULL COMMENT '评论者id',
    content tinytext NOT NULL COMMENT '评论内容',
    comment_parent int(11) DEFAULT NULL COMMENT '评论的父ID关联本表中comment_id（评论的对象，为NULL指对动态的评论）',
    comment_like int(11) DEFAULT NULL COMMENT '评论点赞数量',
    comment_like_user varchar(255) DEFAULT NULL COMMENT '评论点赞用户名（多个用户之间使用“，”分割）',
    comment_status int(11) NOT NULL COMMENT '评论的状态（0：正常；1：违规；2：删除）',
    create_time timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
    CONSTRAINT  c_user_comment_article_id
        FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT  c_article_comment_id
        FOREIGN KEY (article_id) REFERENCES article (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '文章评论表'