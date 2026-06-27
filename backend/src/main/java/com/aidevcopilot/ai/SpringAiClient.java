package com.aidevcopilot.ai;

import com.aidevcopilot.exception.BusinessException;
import com.aidevcopilot.exception.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * Spring AI 客户端适配器。
 *
 * <p>负责将平台内部统一的 {@link AiRequest} 转换为 Spring AI 调用，
 * 并将模型返回结果转换为 {@link AiResponse}。当前第一阶段仅保留扩展点，
 * 不接入真实模型，避免基础框架依赖外部 API Key 才能启动。</p>
 *
 * <p>所属模块：AI 调用封装模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Component
public class SpringAiClient implements AiClient {

    /**
     * 通过 Spring AI 发起对话调用。
     *
     * <p>关键说明：后续所有 Prompt 构建、模型参数控制、调用日志和异常转换
     * 都应集中在该方法或其私有辅助方法内完成，业务层不得绕过该封装。</p>
     *
     * @param request AI 请求参数
     * @return AI 响应结果
     * @throws BusinessException 当前阶段未实现真实 AI 调用时抛出
     */
    @Override
    public AiResponse chat(AiRequest request) {
        throw new BusinessException(ErrorCode.FEATURE_NOT_IMPLEMENTED, "Spring AI chat call is reserved for later phases.");
    }
}
