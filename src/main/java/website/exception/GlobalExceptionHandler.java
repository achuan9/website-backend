package website.exception;

import website.dto.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理验证异常
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public ResultDTO<String> handleValidException(Exception e) {
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        }
        String message = null;
        if (bindingResult != null && bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getDefaultMessage();
            }
        }
        return ResultDTO.validateFailed(message);
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResultDTO<String> handleBusinessException(BusinessException e) {
        LOGGER.error("业务异常：", e);
        return ResultDTO.failed(e.getMessage());
    }

    /**
     * 处理未知异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResultDTO<String> handleException(Exception e) {
        LOGGER.error("系统异常：", e);
        
        // 开发环境下返回详细错误信息
        String errorMessage = "系统异常：" + e.getClass().getSimpleName() + " - " + e.getMessage();
        
        // 生产环境下可替换为：
        // String errorMessage = "系统异常，请联系管理员";
        
        return ResultDTO.failed(errorMessage);
    }
} 