# API访问指南

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

### 角色管理接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/roles` | GET | 获取角色列表 |
| `/roles/{id}` | GET | 获取角色详情 |
| `/roles` | POST | 创建角色 |
| `/roles/{id}` | PUT | 更新角色 |
| `/roles/{id}` | DELETE | 删除角色 |

### 网站管理接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/websites` | GET | 获取网站列表 |
| `/websites/{id}` | GET | 获取网站详情 |
| `/websites` | POST | 创建网站 |
| `/websites/{id}` | PUT | 更新网站 |
| `/websites/{id}` | DELETE | 删除网站 |

### 模板管理接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/templates` | GET | 获取模板列表 |
| `/templates/{id}` | GET | 获取模板详情 |
| `/templates` | POST | 创建模板 |
| `/templates/{id}` | PUT | 更新模板 |
| `/templates/{id}` | DELETE | 删除模板 |

### 网站内容管理接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/websites/{websiteId}/contents` | GET | 获取网站内容列表 |
| `/websites/{websiteId}/contents/{id}` | GET | 获取网站内容详情 |
| `/websites/{websiteId}/contents` | POST | 创建网站内容 |
| `/websites/{websiteId}/contents/{id}` | PUT | 更新网站内容 |
| `/websites/{websiteId}/contents/{id}` | DELETE | 删除网站内容 |

### 网站菜单接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/websites/{websiteId}/menus` | GET | 获取网站菜单列表 |
| `/websites/{websiteId}/menus/{id}` | GET | 获取网站菜单详情 |
| `/websites/{websiteId}/menus` | POST | 创建网站菜单 |
| `/websites/{websiteId}/menus/{id}` | PUT | 更新网站菜单 |
| `/websites/{websiteId}/menus/{id}` | DELETE | 删除网站菜单 |

### 网站配置接口

| 接口路径 | 方法 | 说明 |
|------------|------|------|
| `/websites/{websiteId}/config` | GET | 获取网站配置 |
| `/websites/{websiteId}/config` | PUT | 更新网站配置 |

## API测试工具配置

如果您使用API测试工具(如Postman、Apifox等)，请配置:

1. 环境变量中的baseUrl应设置为:
   ```
   http://localhost:8989/api/v1
   ```

2. 接口路径使用相对路径:
   - `/users`
   - `/roles`
   - `/websites`
   等

## Swagger文档访问

API文档可通过以下地址访问:

**主地址**:
```
http://localhost:8989/api/v1/swagger-ui.html
```

**API文档JSON地址**:
```
http://localhost:8989/api/v1/v3/api-docs
```

## 认证与授权

所有API接口（除了登录、注册接口外）都需要在请求头中添加token:

```
Authorization: Bearer {token}
```

## 前端配置

对于前端项目，请配置API基础路径为:

```javascript
// 配置示例
const API_BASE_URL = 'http://localhost:8989/api/v1';
```

如有任何API访问问题，请确认请求URL是否包含正确的基础路径。 