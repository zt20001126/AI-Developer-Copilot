package com.aidevcopilot.ai;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * AI 调用响应对象。
 *
 * <p>用于封装 AI 门面返回的标准化结果，避免业务模块依赖具体模型厂商
 * 的响应结构。后续可扩展 token 消耗、模型版本、工具调用结果等信息。</p>
 *
 * <p>所属模块：AI 调用封装模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Data
@Builder
public class AiResponse {

    /** 模型生成的主要文本内容。 */
    private String content;

    /** 扩展元数据，用于承载 token 用量、模型名称、请求标识等信息。 */
    private Map<String, Object> metadata;
}
