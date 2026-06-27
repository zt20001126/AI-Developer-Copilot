package com.aidevcopilot.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一接口响应对象。
 *
 * <p>所有 REST API 均应返回该结构，保证前端能够稳定解析 code、message
 * 和 data 字段。该类不承载业务逻辑，仅用于响应协议统一。</p>
 *
 * <p>所属模块：通用工具模块。</p>
 *
 * @param <T> 响应数据类型
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /** 响应码，0 表示成功，非 0 表示失败。 */
    private Integer code;

    /** 响应消息，用于展示调用结果或错误原因。 */
    private String message;

    /** 响应数据体，失败时通常为空。 */
    private T data;

    /**
     * 构建无数据体的成功响应。
     *
     * @param <T> 响应数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> success() {
        return new Result<>(0, "success", null);
    }

    /**
     * 构建带数据体的成功响应。
     *
     * @param data 响应数据
     * @param <T> 响应数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(0, "success", data);
    }

    /**
     * 构建失败响应。
     *
     * @param code 失败错误码
     * @param message 失败错误信息
     * @param <T> 响应数据类型
     * @return 失败响应对象
     */
    public static <T> Result<T> failure(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}

