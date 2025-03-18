package website.service.impl;

import website.dto.UserDTO;
import website.dto.UserRegisterParam;
import website.entity.User;
import website.mapper.UserMapper;
import website.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return null;
        }
        return convertFromUser(user);
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userMapper.selectAll();
        return convertUserList(userList);
    }

    @Override
    @Transactional
    public UserDTO register(UserRegisterParam userRegisterParam) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterParam, user);
        // 检查用户名是否已存在
        if (!checkUsername(user.getUsername())) {
            return null;
        }
        // 设置默认状态
        user.setStatus(1);
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        
        // 插入用户
        userMapper.insert(user);
        
        return convertFromUser(user);
    }

    @Override
    @Transactional
    public boolean updateUser(Long id, User user) {
        User existUser = userMapper.selectById(id);
        if (existUser == null) {
            return false;
        }
        
        user.setId(id);
        user.setUpdateTime(new Date());
        
        // 更新用户信息
        int count = userMapper.update(user);
        return count > 0;
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        int count = userMapper.deleteById(id);
        return count > 0;
    }

    @Override
    public boolean checkUsername(String username) {
        // 根据用户名查询用户
        User user = userMapper.selectByUsername(username);
        // 如果用户不存在，则用户名可用
        return user == null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        // 根据用户名查询用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return null;
        }
        return convertFromUser(user);
    }

    /**
     * 将User实体转换为UserDTO
     */
    private UserDTO convertFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    /**
     * 将User列表转换为UserDTO列表
     */
    private List<UserDTO> convertUserList(List<User> userList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(convertFromUser(user));
        }
        return userDTOList;
    }
} 