package website.controller;

import website.dto.ResultDTO;
import website.dto.UserDTO;
import website.dto.UserRegisterParam;
import website.entity.User;
import website.exception.BusinessException;
import website.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理接口
 */
@RestController
@RequestMapping("/users")
@Tag(name = "用户管理", description = "用户管理相关接口")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 获取用户列表
     */
    @GetMapping
    @Operation(summary = "获取用户列表", description = "获取所有用户的列表")
    public ResultDTO<List<UserDTO>> listAllUsers() {
        List<UserDTO> userList = userService.listAllUsers();
        return ResultDTO.success(userList);
    }
    
    /**
     * 根据ID获取用户
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取用户详情", description = "根据用户ID获取用户详细信息")
    public ResultDTO<UserDTO> getUserById(
            @Parameter(description = "用户ID", required = true) @PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return ResultDTO.success(user);
    }
    
    /**
     * 注册用户
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "注册新用户")
    public ResultDTO<UserDTO> register(
            @Parameter(description = "用户注册信息", required = true) @RequestBody UserRegisterParam userRegisterParam) {
        // 校验密码
        if (!userRegisterParam.getPassword().equals(userRegisterParam.getConfirmPassword())) {
            return ResultDTO.validateFailed("两次输入的密码不一致");
        }
        
        // 校验用户名是否可用
        if (!userService.checkUsername(userRegisterParam.getUsername())) {
            return ResultDTO.validateFailed("用户名已被使用");
        }
        
        UserDTO user = userService.register(userRegisterParam);
        if (user == null) {
            return ResultDTO.failed("注册失败");
        }
        return ResultDTO.success(user);
    }
    
    /**
     * 更新用户
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新用户", description = "更新指定ID的用户信息")
    public ResultDTO<String> updateUser(
            @Parameter(description = "用户ID", required = true) @PathVariable Long id,
            @Parameter(description = "用户信息", required = true) @RequestBody User user) {
        boolean success = userService.updateUser(id, user);
        if (success) {
            return ResultDTO.success("更新成功");
        } else {
            return ResultDTO.failed("更新失败");
        }
    }
    
    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "删除指定ID的用户")
    public ResultDTO<String> deleteUser(
            @Parameter(description = "用户ID", required = true) @PathVariable Long id) {
        boolean success = userService.deleteUser(id);
        if (success) {
            return ResultDTO.success("删除成功");
        } else {
            return ResultDTO.failed("删除失败");
        }
    }
    
    /**
     * 检查用户名是否可用
     */
    @GetMapping("/checkUsername")
    @Operation(summary = "检查用户名", description = "检查用户名是否可用")
    public ResultDTO<Boolean> checkUsername(
            @Parameter(description = "用户名", required = true) @RequestParam String username) {
        boolean available = userService.checkUsername(username);
        return ResultDTO.success(available);
    }
} 