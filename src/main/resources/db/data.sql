-- 清空并重新插入测试数据
TRUNCATE TABLE `user`;
TRUNCATE TABLE `news`;
TRUNCATE TABLE `product`;
TRUNCATE TABLE `product_category`;
TRUNCATE TABLE `contact`;
TRUNCATE TABLE `company_info`;

-- 插入测试用户
INSERT INTO `user` (`username`, `password`, `real_name`, `phone`, `email`, `role`, `status`, `create_time`, `update_time`)
VALUES 
('admin', '123456', '管理员', '13800138000', 'admin@company.com', 'admin', 1, NOW(), NOW()),
('editor', '123456', '编辑', '13900139000', 'editor@company.com', 'admin', 1, NOW(), NOW()),
('test', '123456', '测试用户', '13700137000', 'test@company.com', 'user', 1, NOW(), NOW());

-- 插入测试新闻
INSERT INTO `news` (`title`, `content`, `summary`, `cover_image`, `author`, `view_count`, `is_top`, `status`, `create_time`, `update_time`)
VALUES
('公司成立十周年庆典', '<p>公司成立十周年庆典活动圆满举行，感谢各位客户和合作伙伴的支持...</p>', '公司成立十周年庆典活动圆满举行', '/uploads/news/news1.jpg', '管理员', 100, 1, 1, NOW(), NOW()),
('新产品发布会', '<p>我们很高兴地宣布，新一代产品将于下月正式发布...</p>', '新一代产品即将发布', '/uploads/news/news2.jpg', '编辑', 80, 0, 1, NOW(), NOW()),
('行业技术交流会', '<p>公司参加了行业技术交流会，分享了最新的技术成果...</p>', '公司参加行业技术交流会', '/uploads/news/news3.jpg', '编辑', 50, 0, 1, NOW(), NOW());

-- 插入产品分类
INSERT INTO `product_category` (`name`, `description`, `sort_order`, `parent_id`, `create_time`, `update_time`)
VALUES
('软件产品', '公司开发的各类软件产品', 1, 0, NOW(), NOW()),
('硬件产品', '公司销售的各类硬件产品', 2, 0, NOW(), NOW()),
('解决方案', '公司提供的整体解决方案', 3, 0, NOW(), NOW()),
('企业应用', '面向企业的应用软件', 1, 1, NOW(), NOW()),
('消费应用', '面向消费者的应用软件', 2, 1, NOW(), NOW());

-- 插入测试产品
INSERT INTO `product` (`name`, `description`, `price`, `cover_image`, `category_id`, `is_featured`, `status`, `create_time`, `update_time`)
VALUES
('企业管理系统', '一站式企业管理解决方案，包含人事、财务、库存等模块', 19999.00, '/uploads/products/product1.jpg', 4, 1, 1, NOW(), NOW()),
('智能家居控制器', '通过手机APP控制家中各种智能设备', 1299.00, '/uploads/products/product2.jpg', 2, 1, 1, NOW(), NOW()),
('数据分析平台', '强大的数据分析工具，帮助企业挖掘数据价值', 9999.00, '/uploads/products/product3.jpg', 4, 0, 1, NOW(), NOW()),
('移动办公APP', '随时随地处理办公事务，提高工作效率', 0.00, '/uploads/products/product4.jpg', 5, 1, 1, NOW(), NOW());

-- 插入测试联系信息
INSERT INTO `contact` (`name`, `email`, `phone`, `subject`, `message`, `status`, `create_time`, `update_time`)
VALUES
('张先生', 'zhang@example.com', '13812345678', '产品咨询', '我想了解一下企业管理系统的具体功能和价格', 1, NOW(), NOW()),
('李女士', 'li@example.com', '13987654321', '合作意向', '我们公司希望与贵公司建立长期合作关系', 0, NOW(), NOW()),
('王先生', 'wang@example.com', '13600001111', '技术支持', '使用产品过程中遇到了一些问题，需要技术支持', 0, NOW(), NOW());

-- 插入公司信息
INSERT INTO `company_info` (`name`, `logo`, `description`, `address`, `phone`, `email`, `website`, `create_time`, `update_time`)
VALUES
('示例科技有限公司', '/uploads/logo.png', '示例科技有限公司成立于2010年，是一家专注于软件开发和IT解决方案的高新技术企业。公司拥有一支高素质的研发团队，致力于为客户提供优质的产品和服务。', '北京市朝阳区科技园区88号', '400-123-4567', 'contact@example.com', 'www.example.com', NOW(), NOW()); 