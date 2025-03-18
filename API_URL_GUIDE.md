# 企业官网API访问指南

## API基础路径

项目API基础路径为: `/api/v1`

## API路径列表

所有API接口都需要添加 `/api/v1` 前缀:

### 用户管理接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/users` | GET | 获取用户列表 |
| `/users/{id}` | GET | 获取用户详情 |
| `/users/register` | POST | 注册用户 |
| `/users/{id}` | PUT | 更新用户 |
| `/users/{id}` | DELETE | 删除用户 |
| `/users/checkUsername` | GET | 检查用户名 |

### 新闻管理接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/news` | GET | 获取新闻列表 |
| `/news/{id}` | GET | 获取新闻详情 |
| `/news` | POST | 创建新闻 |
| `/news/{id}` | PUT | 更新新闻 |
| `/news/{id}` | DELETE | 删除新闻 |

### 产品管理接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/products` | GET | 获取产品列表 |
| `/products/{id}` | GET | 获取产品详情 |
| `/products` | POST | 创建产品 |
| `/products/{id}` | PUT | 更新产品 |
| `/products/{id}` | DELETE | 删除产品 |

### 产品分类接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/product-categories` | GET | 获取产品分类列表 |
| `/product-categories/{id}` | GET | 获取产品分类详情 |
| `/product-categories` | POST | 创建产品分类 |
| `/product-categories/{id}` | PUT | 更新产品分类 |
| `/product-categories/{id}` | DELETE | 删除产品分类 |

### 联系我们接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/contacts` | GET | 获取联系信息列表 |
| `/contacts/{id}` | GET | 获取联系信息详情 |
| `/contacts` | POST | 提交联系信息 |
| `/contacts/{id}` | PUT | 更新联系信息状态 |
| `/contacts/{id}` | DELETE | 删除联系信息 |

### 公司信息接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/company-info` | GET | 获取公司信息 |
| `/company-info` | PUT | 更新公司信息 |

## API测试工具配置

如果您使用API测试工具(如Postman、Apifox等)，请配置:

1. 环境变量中的baseUrl应设置为:
   ```
   http://localhost:8989/api/v1
   ```

2. 接口路径使用相对路径:
   - `/users`
   - `/news`
   - `/products`
   等

## Swagger文档访问

API文档可通过以下地址访问:

**主地址**:
```
http://localhost:8989/api/v1/swagger-ui.html
```

**备用地址**:
```
http://localhost:8989/api/v1/swagger-ui/index.html
```

**API文档JSON地址**:
```
http://localhost:8989/api/v1/v3/api-docs
```

**访问提示**:
- 如果遇到404错误，请先确认应用是否正常启动
- 检查控制台日志中是否有SpringDoc相关错误
- 可能需要清除浏览器缓存再次尝试访问

## 前端项目配置

对于前端项目(`@company/website`和`@company/admin`)，请配置API基础路径为:

```javascript
// 配置示例
const API_BASE_URL = 'http://localhost:8989/api/v1';
```

如有任何API访问问题，请确认请求URL是否包含正确的基础路径。 