package com.aidevcopilot.common.exception;

import lombok.Getter;

/**
 * 业务异常。
 *
 * <p>用于 Service、Workflow、AI 调用封装等业务相关模块主动抛出可预期异常，
 * 并由全局异常处理器转换为统一响应结构。</p>
 *
 * <p>所属模块：异常处理模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Getter
public class BusinessException extends RuntimeException {

    /** 业务错误码，用于前端或调用方识别异常类型。 */
    private final Integer code;

    /**
     * 根据标准错误码创建业务异常。
     *
     * @param errorCode 标准错误码枚举
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * 根据标准错误码和自定义错误信息创建业务异常。
     *
     * @param errorCode 标准错误码枚举
     * @param message 自定义错误信息
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}

