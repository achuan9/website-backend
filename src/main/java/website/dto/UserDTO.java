package website.dto;

import java.io.Serializable;
import website.enums.UserStatus;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户数据传输对象
 */
@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 头像地址
     */
    private String avatar;
    
    /**
     * 用户状态
     */
    private UserStatus status;
    
    /**
     * 获取状态显示文本
     */
    public String getStatusLabel() {
        return status != null ? status.getLabel() : null;
    }
} 