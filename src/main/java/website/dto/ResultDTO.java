package website.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 统一API响应结果封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "API统一响应结果")
public class ResultDTO<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @Schema(description = "状态码", example = "200")
    private Integer code;
    
    /**
     * 消息
     */
    @Schema(description = "消息", example = "操作成功")
    private String message;
    
    /**
     * 数据
     */
    @Schema(description = "响应数据")
    private T data;

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功返回结果
     */
    public static <T> ResultDTO<T> success() {
        return new ResultDTO<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param message 提示信息
     */
    public static <T> ResultDTO<T> success(T data, String message) {
        return new ResultDTO<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> ResultDTO<T> failed(IErrorCode errorCode) {
        return new ResultDTO<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> ResultDTO<T> failed(IErrorCode errorCode, String message) {
        return new ResultDTO<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ResultDTO<T> failed(String message) {
        return new ResultDTO<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResultDTO<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResultDTO<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> ResultDTO<T> validateFailed(String message) {
        return new ResultDTO<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResultDTO<T> unauthorized(T data) {
        return new ResultDTO<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResultDTO<T> forbidden(T data) {
        return new ResultDTO<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }
    
    /**
     * 资源不存在返回结果
     */
    public static <T> ResultDTO<T> notFound(String message) {
        return new ResultDTO<T>(ResultCode.NOT_FOUND.getCode(), message, null);
    }
    
    /**
     * 数据冲突返回结果
     */
    public static <T> ResultDTO<T> conflict(String message) {
        return new ResultDTO<T>(ResultCode.CONFLICT.getCode(), message, null);
    }
} 