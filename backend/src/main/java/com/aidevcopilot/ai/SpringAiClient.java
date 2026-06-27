package com.aidevcopilot.ai;

import com.aidevcopilot.common.exception.BusinessException;
import com.aidevcopilot.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

/**
 * Spring AI 客户端适配器。
 *
 * <p>负责将平台内部统一的 {@link AiRequest} 转换为 Spring AI 调用，
 * 并将模型返回结果转换为 {@link AiResponse}。DeepSeek 兼容 OpenAI API，
 * 因此当前通过 Spring AI OpenAI ChatModel 完成真实大模型调用。</p>
 *
 * <p>所属模块：AI 调用封装模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Component
@RequiredArgsConstructor
public class SpringAiClient implements AiClient {

    /** Spring AI 对话模型，由配置文件中的 DeepSeek/OpenAI-compatible 配置自动装配。 */
    private final ChatModel chatModel;

    /**
     * 通过 Spring AI 发起对话调用。
     *
     * <p>关键说明：后续所有 Prompt 构建、模型参数控制、调用日志和异常转换
     * 都应集中在该方法或其私有辅助方法内完成，业务层不得绕过该封装。</p>
     *
     * @param request AI 请求参数
     * @return AI 响应结果
     * @throws BusinessException 模型调用失败时抛出
     */
    @Override
    public AiResponse chat(AiRequest request) {
        try {
            // 先把平台内部统一的 AiRequest 转换为模型可直接理解的 Prompt 文本。
            String prompt = buildPrompt(request);

            // 这里是真正的大模型调用点。
            // ChatModel 由 Spring AI 根据 application.yml 中的 DeepSeek/OpenAI-compatible 配置自动创建。
            String content = chatModel.call(prompt);

            // 将模型原始文本封装成平台统一响应，避免上层工作流直接依赖 Spring AI 返回结构。
            return AiResponse.builder()
                    .content(content)
                    .build();
        } catch (Exception exception) {
            // 模型调用失败时统一转换为业务异常，由 GlobalExceptionHandler 返回给前端。
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, "大模型调用失败：" + exception.getMessage());
        }
    }

    /**
     * 构建最终发送给模型的 Prompt。
     *
     * <p>当前第一版使用纯文本方式拼接系统提示词和用户提示词。后续可以升级为
     * Spring AI 的 Message/Prompt 对象，以支持多轮对话、结构化输出和工具调用。</p>
     *
     * @param request AI 请求参数
     * @return 最终 Prompt 文本
     */
    private String buildPrompt(AiRequest request) {
        // 没有系统提示词时，直接使用用户 Prompt，适合简单调用场景。
        if (request.getSystemPrompt() == null || request.getSystemPrompt().isBlank()) {
            return request.getPrompt();
        }
        // 有系统提示词时，把模型角色和用户任务显式分开，提升模型理解稳定性。
        return """
                系统角色：
                %s

                用户任务：
                %s
                """.formatted(request.getSystemPrompt(), request.getPrompt());
    }
}

