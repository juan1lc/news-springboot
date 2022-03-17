CREATE TABLE post_comment
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '评论id',
    postid VARCHAR(32) NOT NULL COMMENT '评论对应动态的ID',
    userid VARCHAR(32) NOT NULL COMMENT '评论者id',
    content tinytext NOT NULL COMMENT '评论内容',
    commentparent int(11) DEFAULT NULL COMMENT '评论的父ID关联本表中comment_id（评论的对象，为NULL指对动态的评论）',
    commentlike int(11) DEFAULT NULL COMMENT '评论点赞数量',
    commentlikeuser varchar(255) DEFAULT NULL COMMENT '评论点赞用户名（多个用户之间使用“，”分割）',
    createtime timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
    CONSTRAINT  c_user_comment_post_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_post_comment_id
        FOREIGN KEY (postid) REFERENCES post (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态评论表'