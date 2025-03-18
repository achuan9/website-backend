# 企业官网后端服务 (Company Backend)

本项目是企业官网的后端服务，为前端提供API接口，包括新闻管理、产品管理、用户管理、联系我们等功能。

## 技术栈

- Spring Boot 3.2.0
- MyBatis 3.0.4
- MySQL 8.0+
- SpringDoc OpenAPI 2.4.0 (Swagger)
- Spring Validation

## 项目结构

```
src/main/java/com/company/backend/
├── config/           # 配置类
├── controller/       # 控制器
├── dto/              # 数据传输对象
├── entity/           # 实体类
├── exception/        # 异常处理
├── mapper/           # MyBatis Mapper接口
├── service/          # 服务接口
│   └── impl/         # 服务实现
└── util/             # 工具类
```

## 数据库设置指南

### 1. 数据库配置

本项目使用MySQL数据库。数据库配置信息位于`src/main/resources/application.properties`文件中：

```properties
# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/company_website?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

请根据您的实际环境修改以下配置：
- `spring.datasource.url`: 数据库连接URL，修改主机名、端口和数据库名
- `spring.datasource.username`: 数据库用户名
- `spring.datasource.password`: 数据库密码

### 2. 初始化数据库

1. 创建数据库和表结构

   项目提供了初始化SQL脚本，位于`src/main/resources/db/schema.sql`。您可以使用MySQL客户端工具执行此脚本：

   ```bash
   # Windows CMD
   mysql -u root -p < src/main/resources/db/schema.sql
   
   # Windows PowerShell
   Get-Content src/main/resources/db/schema.sql | mysql -u root -p
   
   # Linux/Mac
   mysql -u root -p < src/main/resources/db/schema.sql
   ```

   或者直接在MySQL客户端中执行脚本内容。

2. 导入测试数据

   项目提供了测试数据脚本，位于`src/main/resources/db/data.sql`。您可以使用MySQL客户端工具执行此脚本：

   ```bash
   # Windows CMD
   mysql -u root -p company_website < src/main/resources/db/data.sql
   
   # Windows PowerShell
   Get-Content src/main/resources/db/data.sql | mysql -u root -p company_website
   
   # Linux/Mac
   mysql -u root -p company_website < src/main/resources/db/data.sql
   ```

3. 数据库表说明

   项目包含以下表：
   - `user`: 用户表，存储系统用户信息
   - `news`: 新闻表，存储公司新闻
   - `product`: 产品表，存储公司产品
   - `product_category`: 产品分类表
   - `contact`: 联系我们表，存储用户留言
   - `company_info`: 公司信息表，存储公司基本信息

## API文档

项目集成了Swagger UI，可以通过以下URL访问API文档：

```
http://localhost:8989/api/v1/swagger-ui.html
```

## 前端项目

本项目对应的前端项目为：

- 官网前台：`@company/website`
- 后台管理系统：`@company/admin`

## 开发与部署

### 开发环境

1. 克隆项目
   ```bash
   git clone <repository-url>
   cd company-backend
   ```

2. 使用IDE导入项目（推荐IntelliJ IDEA或Eclipse）

3. 启动项目
   ```bash
   ./mvnw spring-boot:run
   ```

### 生产环境部署

1. 构建项目
   ```bash
   ./mvnw clean package
   ```

2. 运行JAR文件
   ```bash
   java -jar target/company-backend-0.0.1-SNAPSHOT.jar
   ```

## 配置说明

主要配置文件位于`src/main/resources/application.properties`，包含以下配置：

- 数据库配置
- MyBatis配置
- 服务器端口配置
- Swagger配置
- 日志配置
- 应用程序配置

## 联系方式

如有问题，请联系项目维护人员。 