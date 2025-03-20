package website.service;

import com.baomidou.mybatisplus.extension.service.IService;

import website.dto.UserDTO;
import website.dto.UserRegisterParam;
import website.entity.User;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {
    
    /**
     * 根据ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    UserDTO getUserById(Long id);
    
    /**
     * 获取所有用户列表
     * @return 用户列表
     */
    List<UserDTO> listAllUsers();
    
    /**
     * 注册用户
     * @param userRegisterParam 用户注册参数
     * @return 注册结果
     */
    UserDTO register(UserRegisterParam userRegisterParam);
    
    /**
     * 更新用户信息
     * @param id 用户ID
     * @param user 用户信息
     * @return 更新结果
     */
    boolean updateUser(Long id, User user);
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 删除结果
     */
    boolean deleteUser(Long id);
    
    /**
     * 检查用户名是否可用
     * @param username 用户名
     * @return 是否可用
     */
    boolean checkUsername(String username);
    
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户信息
     */
    UserDTO getUserByUsername(String username);
} 