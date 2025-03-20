package website.mapper;

import website.entity.Role;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色数据访问接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    
    /**
     * 根据网站ID查询角色
     * @param websiteId 网站ID
     * @return 角色列表
     */
    default List<Role> selectByWebsiteId(Long websiteId) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery()
                .eq(Role::getWebsiteId, websiteId);
        return selectList(wrapper);
    }
} 