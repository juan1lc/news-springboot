CREATE TABLE tag
(
    `id` VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '标签ID',
    `name` VARCHAR(128) NOT NULL COMMENT '标签名称',
    `tagdesc` VARCHAR(255) DEFAULT NULL COMMENT '标签简介',
    `create_time` timestamp NOT NULL COMMENT '话题创建时间',
    `discussion` INTEGER NOT NULL COMMENT '讨论量'

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '标签表';