CREATE TABLE tag
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '标签ID',
    name VARCHAR(128) NOT NULL COMMENT '标签名称',
    tag_desc VARCHAR(255) DEFAULT NULL COMMENT '标签简介',
    tag_status int(11) NOT NULL COMMENT '话题状态（0：正常；1：禁用；2：删除）',
    create_time timestamp NOT NULL COMMENT '话题创建时间',
    update_time timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '话题更新时间',
    operator_name VARCHAR(64) NOT NULL COMMENT '主题创建操作人（只记录最后一个操作人）'

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '标签表';