package com.aidevcopilot.exception;

import lombok.Getter;

/**
 * 统一错误码枚举。
 *
 * <p>用于规范后端 API 返回码，避免各模块自行定义魔法数字。
 * 后续新增业务错误码时应在此集中维护，并保持语义清晰。</p>
 *
 * <p>所属模块：异常处理模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Getter
public enum ErrorCode {

    /** 请求成功。 */
    SUCCESS(0, "success"),
    /** 请求参数错误或请求体无法解析。 */
    BAD_REQUEST(400, "bad request"),
    /** 请求资源不存在。 */
    NOT_FOUND(404, "resource not found"),
    /** 功能在当前阶段尚未实现。 */
    FEATURE_NOT_IMPLEMENTED(1001, "feature not implemented"),
    /** 系统内部异常。 */
    INTERNAL_ERROR(500, "internal server error");

    /** 错误码。 */
    private final Integer code;

    /** 错误描述。 */
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
