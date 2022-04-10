CREATE TABLE follower
(
    id VARCHAR(32) NOT NULL
        PRIMARY KEY COMMENT 'id',
    userid1 VARCHAR(32) NOT NULL COMMENT '被关注者id',
    userid2 VARCHAR(32) NOT NULL COMMENT '关注者id',

    CONSTRAINT  c_user_follow_id
        FOREIGN KEY (userid1) REFERENCES user (id),
    CONSTRAINT  c_user_follower_id
        FOREIGN KEY (userid2) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '关注表'