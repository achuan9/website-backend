# 企业官网SaaS系统数据库设计

## 数据库初始化设置

本项目使用Spring Boot的自动初始化数据库功能，无需手动执行SQL脚本。应用启动时会自动创建表结构并插入初始数据。

相关配置位于`application.properties`文件中：

```properties
# SQL初始化
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:db/schema-mysql.sql
spring.sql.init.data-locations=classpath:db/data-mysql.sql
spring.sql.init.continue-on-error=true
```

## 数据库表结构

企业官网SaaS服务后台管理系统包含以下核心表：

### 1. 用户表(User)

存储系统用户信息，包括管理员和普通用户

```sql
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0=禁用，1=启用',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
```

### 2. 角色表(Role)

存储系统角色与网站特定角色

```sql
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `code` varchar(50) NOT NULL COMMENT '角色编码',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
```

### 3. 用户角色关联表(UserRole)

用户与角色的多对多关联表

```sql
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';
```

### 4. 模板表(Template)

存储网站模板信息

```sql
CREATE TABLE `template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '模板名称',
  `description` text COMMENT '模板描述',
  `thumbnail` varchar(255) DEFAULT NULL COMMENT '模板缩略图',
  `repo_url` varchar(255) DEFAULT NULL COMMENT '前端工程仓库地址',
  `config_schema` json DEFAULT NULL COMMENT '模板配置项结构',
  `menu_structure` json DEFAULT NULL COMMENT '预设菜单结构',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板表';
```

### 5. 网站表(Website)

存储创建的网站信息

```sql
CREATE TABLE `website` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '网站名称',
  `domain` varchar(100) DEFAULT NULL COMMENT '网站域名',
  `template_id` bigint(20) NOT NULL COMMENT '使用的模板ID',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0=开发中，1=已发布',
  `created_by` bigint(20) NOT NULL COMMENT '创建者ID',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_template_id` (`template_id`),
  KEY `idx_created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网站表';
```

### 6. 网站配置表(WebsiteConfig)

存储网站的配置信息

```sql
CREATE TABLE `website_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `website_id` bigint(20) NOT NULL COMMENT '网站ID',
  `primary_color` varchar(20) DEFAULT NULL COMMENT '主题色',
  `logo_url` varchar(255) DEFAULT NULL COMMENT 'LOGO图片地址',
  `other_configs` json DEFAULT NULL COMMENT '其他配置项',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_website_id` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网站配置表';
```

### 7. 网站内容表(WebsiteContent)

存储网站的具体内容

```sql
CREATE TABLE `website_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `website_id` bigint(20) NOT NULL COMMENT '网站ID',
  `page_key` varchar(100) NOT NULL COMMENT '页面标识符',
  `section_key` varchar(100) NOT NULL COMMENT '页面区块标识符',
  `content_type` varchar(50) NOT NULL COMMENT '内容类型：text/image/video',
  `content` text COMMENT '具体内容',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_website_id` (`website_id`),
  KEY `idx_page_section` (`page_key`,`section_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网站内容表';
```

### 8. 网站菜单表(WebsiteMenu)

存储网站的菜单结构

```sql
CREATE TABLE `website_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `website_id` bigint(20) NOT NULL COMMENT '网站ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `title` varchar(100) NOT NULL COMMENT '菜单标题',
  `link` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `is_visible` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_website_id` (`website_id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网站菜单表';
```

## 初始数据说明

系统初始化时会创建以下数据：

### 用户数据

| 用户名 | 密码   | 真实姓名 | 状态 |
|--------|--------|----------|------|
| admin  | 123456 | 管理员   | 启用 |
| test   | 123456 | 测试用户 | 启用 |

### 角色数据

| 角色名称 | 角色编码 | 描述 |
|----------|----------|------|
| 管理员   | admin    | 系统管理员 |
| 普通用户 | user     | 普通用户 |

## 数据库扩展

随着项目的发展，数据库结构可能会扩展以支持更多功能：

1. 权限表(Permission) - 细粒度的权限控制
2. 角色权限关联表(RolePermission) - 角色与权限的多对多关系
3. 网站访问统计表(WebsiteStatistics) - 网站流量和用户行为分析
4. 插件表(Plugin) - 支持模板功能扩展
