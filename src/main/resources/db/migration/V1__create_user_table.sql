CREATE TABLE user
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT '用户ID',
    role_id VARCHAR(32) NOT NULL COMMENT '用户角色',
    username VARCHAR(64) NOT NULL COMMENT '用户名称',
    mail VARCHAR(64) NULL COMMENT '邮箱地址',
    phone VARCHAR(64) NOT NULL COMMENT '手机号码',
    password VARCHAR(64) NOT NULL COMMENT '密码',
    birth varchar(12) NULL COMMENT '生日',
    address varchar(255) NULL COMMENT '地址：如广东广州',
    gender VARCHAR(255) NULL COMMENT '性别',
    photo VARCHAR(255) NOT NULL COMMENT '头像',
    introduction VARCHAR(255) NOT NULL COMMENT '个人简介',
    CONSTRAINT uk_user_username
        UNIQUE (username),
    UNIQUE KEY user_phone_uq (phone) USING BTREE COMMENT '手机号码唯一索引',
    KEY role_id_fk (role_id) USING BTREE COMMENT '角色ID外键',
    CONSTRAINT user_ibfk_1 FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户表';

