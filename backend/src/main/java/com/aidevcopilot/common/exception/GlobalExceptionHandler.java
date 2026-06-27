package com.aidevcopilot.common.exception;

import com.aidevcopilot.common.result.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器。
 *
 * <p>负责统一捕获 Controller 层向外抛出的异常，并转换为 {@link Result}
 * 响应结构，避免异常堆栈直接暴露给前端。</p>
 *
 * <p>关键说明：业务异常按业务错误码返回；参数异常按 BAD_REQUEST 返回；
 * 未预期异常记录错误日志后返回 INTERNAL_ERROR。</p>
 *
 * <p>所属模块：异常处理模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常。
     *
     * @param exception 业务层或工作流主动抛出的异常
     * @return 统一失败响应
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException exception) {
        return Result.failure(exception.getCode(), exception.getMessage());
    }

    /**
     * 处理请求参数异常。
     *
     * @param exception 参数校验、参数绑定或请求体解析异常
     * @return 统一失败响应
     */
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BindException.class,
            ConstraintViolationException.class,
            HttpMessageNotReadableException.class
    })
    public Result<Void> handleBadRequest(Exception exception) {
        return Result.failure(ErrorCode.BAD_REQUEST.getCode(), exception.getMessage());
    }

    /**
     * 处理未预期系统异常。
     *
     * @param exception 未被前置处理器捕获的异常
     * @return 统一失败响应
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception exception) {
        // 未预期异常必须记录完整堆栈，便于线上问题定位；返回给前端的信息保持收敛。
        log.error("Unhandled exception", exception);
        return Result.failure(ErrorCode.INTERNAL_ERROR.getCode(), ErrorCode.INTERNAL_ERROR.getMessage());
    }
}

