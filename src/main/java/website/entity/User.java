package website.entity;

import java.util.Date;
import website.enums.UserStatus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 用户实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@Schema(description = "用户实体")
public class User extends Model<User> {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "用户ID")
    private Long id;
    
    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "admin")
    private String username;
    
    /**
     * 密码
     */
    @Schema(description = "密码", example = "******")
    private String password;
    
    /**
     * 真实姓名
     */
    @TableField("real_name")
    @Schema(description = "真实姓名", example = "张三")
    private String realName;
    
    /**
     * 手机号
     */
    @Schema(description = "手机号", example = "13812345678")
    private String phone;
    
    /**
     * 邮箱
     */
    @Schema(description = "邮箱", example = "example@example.com")
    private String email;
    
    /**
     * 用户状态：0-禁用，1-启用
     */
    @Schema(description = "用户状态")
    private UserStatus status;
    
    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String avatar;
    
    /**
     * 逻辑删除标志：0-未删除，1-已删除
     */
    @TableLogic
    @Schema(description = "是否删除", hidden = true)
    private Integer deleted;
    
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