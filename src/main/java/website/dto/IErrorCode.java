package website.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * API错误码接口
 */
@Schema(description = "API错误码接口")
public interface IErrorCode {
    /**
     * 获取错误码
     */
    @Schema(description = "错误码")
    Integer getCode();

    /**
     * 获取错误信息
     */
    @Schema(description = "错误信息")
    String getMessage();
} 