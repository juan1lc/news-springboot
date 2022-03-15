INSERT INTO `role` (id, name, title) VALUES ('1', 'ROLE_USER', '普通用户');
INSERT INTO `role` (id, name, title) VALUES ('2', 'ROLE_ADMIN', '管理员');
INSERT INTO `role` (id, name, title) VALUES ('3', 'ROLE_MEDIA', '媒体人');
INSERT INTO `user` (id, role_id, username, phone, password, gender, photo, introduction) VALUES ('1','2', 'admin','15270393099', '$2a$10$J.5W8HVYzFadywg7pUF5m.UILVaapyJzhCHOJbgeWqo8h.08mGF0e', 'UNKNOWN','src/main/resources/static/images/defaulthead.jpg', '管理员');