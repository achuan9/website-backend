package website.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 用户状态枚举
 */
@Schema(description = "用户状态枚举")
public enum UserStatus implements IEnum<Integer> {
    
    /**
     * 禁用状态
     */
    @Schema(description = "禁用")
    DISABLED(0, "禁用"),
    
    /**
     * 启用状态
     */
    @Schema(description = "启用")
    ENABLED(1, "启用");
    
    private final Integer value;
    private final String label;
    
    UserStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
    /**
     * 获取状态描述
     */
    public String getLabel() {
        return this.label;
    }
    
    /**
     * 实现IEnum接口的getValue方法
     */
    @Override
    public Integer getValue() {
        return this.value;
    }
    
    /**
     * 根据状态码获取枚举实例
     */
    public static UserStatus getByValue(Integer value) {
        if (value == null) {
            return null;
        }
        
        for (UserStatus status : UserStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        
        return null;
    }
    
    /**
     * 判断状态码是否有效
     */
    public static boolean isValidValue(Integer value) {
        return getByValue(value) != null;
    }
} 