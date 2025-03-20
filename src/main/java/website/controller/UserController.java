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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

/**
 * 用户管理接口
 */
@RestController
@RequestMapping("/users")
@Tag(name = "用户管理", description = "用户管理相关接口")
@Validated
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 获取用户列表
     */
    @GetMapping
    @Operation(summary = "获取用户列表", description = "获取所有用户的列表")
    @ApiResponse(responseCode = "200", description = "成功获取用户列表")
    public ResultDTO<List<UserDTO>> listAllUsers() {
        List<UserDTO> userList = userService.listAllUsers();
        return ResultDTO.success(userList);
    }
    
    /**
     * 根据ID获取用户
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取用户详情", description = "根据用户ID获取用户详细信息")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "成功获取用户详情"),
        @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    public ResultDTO<UserDTO> getUserById(
            @Parameter(description = "用户ID", required = true) 
            @PathVariable Long id) {
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
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "注册成功"),
        @ApiResponse(responseCode = "400", description = "参数校验失败或用户名已存在")
    })
    public ResultDTO<UserDTO> register(
            @Parameter(description = "用户注册信息", required = true) 
            @Valid @RequestBody UserRegisterParam userRegisterParam) {
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
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "更新成功"),
        @ApiResponse(responseCode = "400", description = "参数校验失败"),
        @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    public ResultDTO<String> updateUser(
            @Parameter(description = "用户ID", required = true) 
            @PathVariable Long id,
            @Parameter(description = "用户信息", required = true) 
            @Valid @RequestBody User user) {
        boolean success = userService.updateUser(id, user);
        if (success) {
            return ResultDTO.success("更新成功");
        } else {
            return ResultDTO.failed("更新失败，用户可能不存在");
        }
    }
    
    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "删除指定ID的用户")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "删除成功"),
        @ApiResponse(responseCode = "400", description = "删除失败")
    })
    public ResultDTO<String> deleteUser(
            @Parameter(description = "用户ID", required = true) 
            @PathVariable Long id) {
        boolean success = userService.deleteUser(id);
        if (success) {
            return ResultDTO.success("删除成功");
        } else {
            return ResultDTO.failed("删除失败，用户可能不存在");
        }
    }
    
    /**
     * 检查用户名是否可用
     */
    @GetMapping("/checkUsername")
    @Operation(summary = "检查用户名", description = "检查用户名是否可用")
    @ApiResponse(responseCode = "200", description = "成功查询用户名可用状态")
    public ResultDTO<Boolean> checkUsername(
            @Parameter(description = "用户名", required = true) 
            @NotBlank(message = "用户名不能为空")
            @Pattern(regexp = "^[a-zA-Z0-9_]{4,20}$", message = "用户名必须是4-20位字母、数字或下划线")
            @RequestParam String username) {
        boolean available = userService.checkUsername(username);
        return ResultDTO.success(available);
    }
} 