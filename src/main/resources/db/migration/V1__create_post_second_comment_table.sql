CREATE TABLE post_second_comment
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '评论id',
    commentid VARCHAR(32) NOT NULL COMMENT '评论对应一级评论的ID',
    userid VARCHAR(32) NOT NULL COMMENT '评论者id',
    content tinytext NOT NULL COMMENT '评论内容',
    commentparent VARCHAR(32) DEFAULT NULL COMMENT '评论的父ID（二级评论不记录，三级评论记录）',
    commentlike int(11) DEFAULT NULL COMMENT '评论点赞数量',
    createtime timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
    CONSTRAINT  c_user_comment_post_comment_id
        FOREIGN KEY (userid) REFERENCES user (id),
    CONSTRAINT  c_comment_post_comment_id
        FOREIGN KEY (commentid) REFERENCES post_comment (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '动态二级评论表'