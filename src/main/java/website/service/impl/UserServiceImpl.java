package website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import website.dto.UserDTO;
import website.dto.UserRegisterParam;
import website.entity.User;
import website.enums.UserStatus;
import website.mapper.UserMapper;
import website.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserDTO getUserById(Long id) {
        User user = this.getById(id);
        return user != null ? convertFromUser(user) : null;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = this.list();
        return userList.stream()
                .map(this::convertFromUser)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO register(UserRegisterParam userRegisterParam) {
        // 检查用户名是否已存在
        if (!checkUsername(userRegisterParam.getUsername())) {
            return null;
        }
        
        User user = new User();
        BeanUtils.copyProperties(userRegisterParam, user);
        
        // 设置默认状态为启用
        user.setStatus(UserStatus.ENABLED);
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        
        // 插入用户
        boolean success = this.save(user);
        return success ? convertFromUser(user) : null;
    }

    @Override
    @Transactional
    public boolean updateUser(Long id, User user) {
        User existUser = this.getById(id);
        if (existUser == null) {
            return false;
        }
        
        // 设置ID和更新时间
        user.setId(id);
        user.setUpdateTime(new Date());
        
        // 清除敏感字段，防止被修改
        user.setUsername(null);
        
        // 仅更新非空字段
        return this.updateById(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        return this.removeById(id);
    }

    @Override
    public boolean checkUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return false;
        }
        
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username);
        
        return this.count(wrapper) == 0;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }
        
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username);
        
        User user = this.getOne(wrapper);
        return user != null ? convertFromUser(user) : null;
    }

    /**
     * 将User实体转换为UserDTO
     */
    private UserDTO convertFromUser(User user) {
        if (user == null) {
            return null;
        }
        
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
} 