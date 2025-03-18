package website.mapper;

import website.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据访问接口
 */
@Mapper
public interface UserMapper {
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户对象
     */
    User selectById(@Param("id") Long id);
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> selectAll();
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User selectByUsername(@Param("username") String username);
    
    /**
     * 插入用户
     * @param user 用户对象
     * @return 影响行数
     */
    int insert(User user);
    
    /**
     * 更新用户
     * @param user 用户对象
     * @return 影响行数
     */
    int update(User user);
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);
} 