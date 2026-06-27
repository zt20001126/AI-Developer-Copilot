package com.aidevcopilot.ai;

/**
 * AI 模型调用统一门面。
 *
 * <p>用于屏蔽具体大模型 SDK 或 Spring AI 客户端细节，业务代码、
 * Service 和 Workflow 节点均不得直接调用模型，只能依赖该接口完成
 * AI 能力访问，便于后续统一治理鉴权、限流、日志、重试和模型切换。</p>
 *
 * <p>所属模块：AI 调用封装模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
public interface AiClient {

    /**
     * 发起一次对话模型调用。
     *
     * @param request AI 请求参数，包含用户提示词、系统提示词和扩展元数据
     * @return AI 响应结果，包含模型输出内容和扩展元数据
     * @throws com.aidevcopilot.exception.BusinessException 当模型能力尚未实现或调用失败时抛出
     */
    AiResponse chat(AiRequest request);
}
