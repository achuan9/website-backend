-- 插入初始用户数据
INSERT INTO `user` (`id`, `username`, `password`, `real_name`, `phone`, `email`, `status`, `avatar`, `deleted`, `create_time`, `update_time`) 
VALUES (1, 'admin', '123456', '管理员', '13800138000', 'admin@example.com', 1, 'https://example.com/avatar/admin.jpg', 0, NOW(), NOW());

INSERT INTO `user` (`id`, `username`, `password`, `real_name`, `phone`, `email`, `status`, `avatar`, `deleted`, `create_time`, `update_time`) 
VALUES (2, 'test', '123456', '测试用户', '13900139000', 'test@example.com', 1, 'https://example.com/avatar/test.jpg', 0, NOW(), NOW());

-- 插入初始角色数据
INSERT INTO `role` (`id`, `name`, `code`, `description`, `deleted`, `create_time`, `update_time`) 
VALUES (1, '管理员', 'ADMIN', '系统管理员', 0, NOW(), NOW());

INSERT INTO `role` (`id`, `name`, `code`, `description`, `deleted`, `create_time`, `update_time`) 
VALUES (2, '普通用户', 'USER', '普通用户', 0, NOW(), NOW());

-- 插入用户角色关联数据
INSERT INTO `user_role` (`user_id`, `role_id`, `create_time`) 
VALUES (1, 1, NOW());

INSERT INTO `user_role` (`user_id`, `role_id`, `create_time`) 
VALUES (2, 2, NOW()); 