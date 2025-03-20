package website.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 角色实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("role")
@Schema(description = "角色实体")
public class Role extends Model<Role> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "角色ID")
    private Long id;
    
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;
    
    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String code;
    
    /**
     * 网站ID
     */
    @TableField("website_id")
    @Schema(description = "网站ID")
    private Long websiteId;
    
    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String description;
    
    /**
     * 创建时间
     */
    @TableField("create_time")
    @Schema(description = "创建时间")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @TableField("update_time")
    @Schema(description = "更新时间")
    private Date updateTime;
}
