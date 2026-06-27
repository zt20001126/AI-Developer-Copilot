package com.aidevcopilot.ai;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * AI 调用请求对象。
 *
 * <p>用于承载业务层或工作流节点传入 AI 门面的标准化请求数据，
 * 后续可扩展模型参数、上下文记忆、工具调用参数等内容。</p>
 *
 * <p>所属模块：AI 调用封装模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Data
@Builder
public class AiRequest {

    /** 用户提示词，通常由业务输入和 Prompt 模板组合生成。 */
    private String prompt;

    /** 系统提示词，用于约束模型角色、输出格式和安全边界。 */
    private String systemPrompt;

    /** 扩展元数据，用于传递模型名称、温度、链路追踪标识等非固定参数。 */
    private Map<String, Object> metadata;
}
