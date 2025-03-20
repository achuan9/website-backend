# 企业官网SaaS服务后台管理系统

本项目是一个企业官网的SaaS服务后台管理系统，提供模板化网站快速构建与管理功能。

## 技术栈

### 后端架构
- **核心框架**: Spring Boot 3.0.5
- **ORM框架**: MyBatis-Plus 3.5.0
- **数据库**: MySQL 8.0+
- **API文档**: SpringDoc OpenAPI 3.0 (Swagger UI)
- **数据校验**: Spring Validation
- **代码简化**: Lombok
- **JSON处理**: Jackson
- **日志框架**: SLF4J + Logback

### 安全框架
- **认证授权**: Spring Security
- **JWT**: JJWT 0.11.5
- **密码加密**: BCrypt

### 工具库
- **通用工具**: Apache Commons (Lang3, IO, Collections)
- **日期处理**: Java 8 Date/Time API
- **HTTP客户端**: OkHttp3

### 数据库相关
- **数据库连接池**: HikariCP
- **数据库迁移**: Spring Boot SQL Init
- **数据库审计**: JPA EntityListeners

### 缓存策略
- **本地缓存**: Caffeine
- **分布式缓存**: Redis (可选)

### 存储服务
- **文件上传**: Spring MultipartFile
- **对象存储**: MinIO (可选)

### 开发工具
- **项目构建**: Maven
- **热部署**: Spring DevTools
- **单元测试**: JUnit 5 + Mockito
- **API测试**: REST Assured
- **代码规范**: CheckStyle

## 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Redis (可选)

## 系统架构

### 核心模块

1. **用户认证与授权模块**
   - 用户注册、登录、密码找回
   - JWT认证
   - RBAC权限模型实现多角色管理
   - 角色切换功能
  
2. **用户与角色管理模块**
   - 用户CRUD
   - 角色CRUD
   - 用户-角色关联管理
   - 权限分配
  
3. **网站管理模块**
   - 网站创建（系统管理员专属）
   - 网站模板选择
   - 自动创建对应网站管理员角色
   - 网站配置信息管理
  
4. **内容管理模块**
   - 根据模板动态生成可编辑区域
   - 内容编辑与发布
   - 多语言支持
   - 媒体资源管理

5. **数据统计与Dashboard模块**
   - 网站访问统计
   - 用户行为分析
   - 数据可视化

## 关于角色

本系统有两类角色：系统管理员、网站运营人员。
- 系统管理员可以新建网站，可以管理用户及角色。
- 运营人员角色可以有多个，每新建一个网站则自动生成一个该网站管理员的角色。

## 系统功能

- 切换角色：同一个账号可以有多种角色。
- 登录、注册、修改用户信息等：所有用户可用。
- 用户管理及角色管理：仅系统管理员可见。
- 网站dashboard。
- 新建网站：仅系统管理员可见，每个网站对应一个预设模板，对应一个管理员角色。
- 网站管理：根据当前角色所属网站的模板菜单生成子菜单，切换具体的子菜单可以编辑该菜单页面的具体内容，仅可修改内容不可改变位置及尺寸相关。

## 数据库设置

### 数据库配置

本项目使用MySQL数据库。数据库配置信息位于`src/main/resources/application.properties`文件中：

```properties
# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/company_website?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 自动初始化数据库

项目配置了自动初始化数据库功能，在`application.properties`中：

```properties
# SQL初始化
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:db/schema-mysql.sql
spring.sql.init.data-locations=classpath:db/data-mysql.sql
spring.sql.init.continue-on-error=true
```

数据库会在应用启动时自动创建表结构并插入初始数据。

## API文档

项目集成了Swagger UI，可以通过以下URL访问API文档：

```
http://localhost:8989/api/v1/swagger-ui.html
```

## 项目结构

```
src/main/java/website/
├── config/           # 配置类
│   ├── security/     # 安全相关配置
│   ├── mybatis/      # MyBatis配置
│   └── swagger/      # API文档配置
├── controller/       # REST控制器
├── dto/              # 数据传输对象
├── entity/           # 实体类
├── enums/            # 枚举类
├── exception/        # 异常处理
├── mapper/           # MyBatis Mapper接口
├── service/          # 服务接口
│   └── impl/         # 服务实现
└── util/             # 工具类
    ├── constants/    # 常量定义
    └── security/     # 安全工具类
```

## 开发与部署

### 开发环境

1. 克隆项目
   ```bash
   git clone <repository-url>
   cd website-backend
   ```

2. 使用IDE导入项目（推荐IntelliJ IDEA）

3. 启动项目
   ```bash
   mvn spring-boot:run
   ```

### 生产环境部署

1. 构建项目
   ```bash
   mvn clean package -DskipTests
   ```

2. 运行JAR文件
   ```bash
   java -jar target/website-0.0.1-SNAPSHOT.jar
   ```

## Maven依赖

主要依赖包括：

```xml
<!-- Spring Boot -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- MyBatis-Plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.0</version>
</dependency>

<!-- MySQL -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- SpringDoc OpenAPI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.15</version>
</dependency>

<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
```

## 系统扩展性

1. 多租户设计：每个网站作为独立租户
2. 插件系统：为模板提供扩展能力
3. API网关：为未来微服务架构做准备
4. 缓存策略：网站内容缓存和用户权限缓存

## 联系方式

如有问题，请联系项目维护人员。 