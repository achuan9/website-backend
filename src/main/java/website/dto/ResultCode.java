package website.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * API返回码枚举
 */
@Getter
@AllArgsConstructor
@Schema(description = "API返回码枚举")
public enum ResultCode implements IErrorCode {
    /**
     * 操作成功
     */
    @Schema(description = "操作成功")
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    @Schema(description = "操作失败")
    FAILED(500, "操作失败"),
    /**
     * 参数检验失败
     */
    @Schema(description = "参数检验失败")
    VALIDATE_FAILED(400, "参数检验失败"),
    /**
     * 暂未登录或token已经过期
     */
    @Schema(description = "未授权")
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 没有相关权限
     */
    @Schema(description = "禁止访问")
    FORBIDDEN(403, "没有相关权限"),
    /**
     * 资源不存在
     */
    @Schema(description = "资源不存在")
    NOT_FOUND(404, "资源不存在"),
    /**
     * 数据冲突
     */
    @Schema(description = "数据冲突")
    CONFLICT(409, "数据冲突");

    private final Integer code;
    private final String message;
} 