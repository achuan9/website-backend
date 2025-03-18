# 企业官网数据库设置指南

## 数据库初始化步骤

以下步骤将帮助您正确设置和初始化企业官网后端服务的数据库。

### 1. 创建数据库和表结构

执行schema.sql脚本创建数据库和表结构：

```bash
# Windows CMD环境
mysql -u root -p < src\main\resources\db\schema.sql

# Windows PowerShell环境
Get-Content src\main\resources\db\schema.sql | mysql -u root -p

# Linux/Mac环境
mysql -u root -p < src/main/resources/db/schema.sql
```

或者手动连接到MySQL，复制schema.sql内容执行：

```bash
# 连接MySQL
mysql -u root -p

# 使用MySQL命令行
source src/main/resources/db/schema.sql
```

### 2. 插入测试数据

执行data.sql脚本插入测试数据：

```bash
# Windows CMD环境
mysql -u root -p company_website < src\main\resources\db\data.sql

# Windows PowerShell环境
Get-Content src\main\resources\db\data.sql | mysql -u root -p company_website

# Linux/Mac环境
mysql -u root -p company_website < src/main/resources/db/data.sql
```

或者手动连接到company_website数据库，复制data.sql内容执行：

```bash
# 连接MySQL并选择数据库
mysql -u root -p company_website

# 使用MySQL命令行
source src/main/resources/db/data.sql
```

### 3. 验证数据库设置

连接到数据库并执行以下查询验证设置是否正确：

```sql
-- 查看所有用户
SELECT * FROM user;

-- 查看所有新闻
SELECT * FROM news;

-- 查看所有产品
SELECT * FROM product;

-- 查看所有产品分类
SELECT * FROM product_category;

-- 查看所有联系信息
SELECT * FROM contact;

-- 查看公司信息
SELECT * FROM company_info;
```

## 数据库表结构说明

企业官网后端服务包含以下数据表：

1. **user表** - 用户信息
   - 存储系统用户信息，包括管理员和普通用户

2. **news表** - 新闻信息
   - 存储公司新闻、公告等内容

3. **product表** - 产品信息
   - 存储公司产品信息

4. **product_category表** - 产品分类
   - 存储产品分类信息，支持多级分类

5. **contact表** - 联系信息
   - 存储用户提交的联系表单信息

6. **company_info表** - 公司信息
   - 存储公司基本信息，如名称、地址、联系方式等

## 常见问题排查

### 如果遇到500错误

1. **检查数据库连接**
   - 确认MySQL服务正在运行
   - 验证application.properties中的连接信息是否正确
   - 确认company_website数据库存在

2. **检查表结构**
   - 确认所有表存在且结构正确
   - 检查字段名是否与Entity对象匹配

3. **检查SQL映射**
   - 确认Mapper XML文件中的SQL语句正确
   - 验证resultMap映射正确

4. **查看应用日志**
   - 检查应用程序启动日志
   - 查找与MyBatis或SQL相关的错误

## 测试数据说明

data.sql脚本插入了以下测试数据：

### 用户数据

| 用户名 | 密码   | 真实姓名 | 角色  | 手机号      | 邮箱                |
|--------|--------|----------|-------|-------------|---------------------|
| admin  | 123456 | 管理员   | admin | 13800138000 | admin@company.com   |
| editor | 123456 | 编辑     | admin | 13900139000 | editor@company.com  |
| test   | 123456 | 测试用户 | user  | 13700137000 | test@company.com    |

### 其他测试数据

- **新闻**: 3条测试新闻
- **产品分类**: 5个测试分类
- **产品**: 4个测试产品
- **联系信息**: 3条测试联系信息
- **公司信息**: 基本公司信息

您可以使用这些测试数据进行API测试和前端开发。
