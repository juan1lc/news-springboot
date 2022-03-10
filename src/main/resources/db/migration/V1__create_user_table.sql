CREATE TABLE user
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(64) NULL COMMENT '用户昵称',
    mail VARCHAR(64) NULL COMMENT '邮箱地址',
    phone VARCHAR(64) NULL COMMENT '手机号码',
    password VARCHAR(64) NOT NULL COMMENT '密码',
    gender VARCHAR(255) NULL COMMENT '性别',
    photo VARCHAR(255) NOT NULL COMMENT '头像',
    introduction VARCHAR(255) NOT NULL COMMENT '个人简介',
    CONSTRAINT uk_user_username
        UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户表';