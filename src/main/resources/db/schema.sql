-- 创建数据库
CREATE DATABASE IF NOT EXISTS company_website DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用数据库
USE company_website;

-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `role` varchar(20) DEFAULT 'user' COMMENT '角色：admin-管理员，user-普通用户',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 创建新闻表
CREATE TABLE IF NOT EXISTS `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新闻ID',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `is_top` tinyint(1) DEFAULT '0' COMMENT '是否置顶：0-否，1-是',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0-草稿，1-已发布',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻表';

-- 创建产品表
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `name` varchar(100) NOT NULL COMMENT '产品名称',
  `description` text COMMENT '产品描述',
  `price` decimal(10,2) DEFAULT NULL COMMENT '产品价格',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `is_featured` tinyint(1) DEFAULT '0' COMMENT '是否推荐：0-否，1-是',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0-下架，1-上架',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品表';

-- 创建产品分类表
CREATE TABLE IF NOT EXISTS `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `description` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `sort_order` int(11) DEFAULT '0' COMMENT '排序',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父分类ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品分类表';

-- 创建联系我们表
CREATE TABLE IF NOT EXISTS `contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '联系ID',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `subject` varchar(100) DEFAULT NULL COMMENT '主题',
  `message` text NOT NULL COMMENT '留言内容',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态：0-未处理，1-已处理',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='联系我们表';

-- 创建公司信息表
CREATE TABLE IF NOT EXISTS `company_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '公司名称',
  `logo` varchar(255) DEFAULT NULL COMMENT '公司logo',
  `description` text COMMENT '公司简介',
  `address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '联系邮箱',
  `website` varchar(100) DEFAULT NULL COMMENT '官网地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司信息表';

-- 插入初始管理员用户
INSERT INTO `user` (`username`, `password`, `real_name`, `phone`, `email`, `role`, `status`, `create_time`, `update_time`)
VALUES ('admin', '123456', '管理员', '13800138000', 'admin@company.com', 'admin', 1, NOW(), NOW()); 