package com.aidevcopilot.workflow.node;

import com.aidevcopilot.ai.AiClient;
import com.aidevcopilot.ai.AiRequest;
import com.aidevcopilot.ai.AiResponse;
import com.aidevcopilot.workflow.constant.WorkflowNodeCodes;
import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * AI 调用节点。
 *
 * <p>当前节点作用：承接 Prompt 并通过 AI 调用门面发起真实大模型调用。</p>
 * <p>输入：prompt。</p>
 * <p>输出：aiResponse。</p>
 * <p>下一节点：ResultParseNode。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Component
@RequiredArgsConstructor
public class AiCallNode extends AbstractWorkflowNode {

    /** AI 调用统一门面，节点不直接依赖具体模型厂商 SDK。 */
    private final AiClient aiClient;

    /**
     * 返回节点编码。
     *
     * @return AI 调用节点编码
     */
    @Override
    public String nodeCode() {
        return WorkflowNodeCodes.AI_CALL;
    }

    /**
     * 执行 AI 调用逻辑。
     *
     * <p>真实模型接入时，应在此节点通过 AiClient 发起调用，不允许绕过 AI 封装层。</p>
     *
     * @param context 工作流上下文
     */
    @Override
    public void execute(WorkflowContext context) {
        // 从 prompt_build 节点读取最终 Prompt。缺少 Prompt 说明上游节点没有正确执行，应立即失败。
        String prompt = requiredVariable(context, WorkflowVariableKeys.PROMPT, String.class);

        // 真实大模型调用必须通过 AiClient 门面完成。
        // 这样工作流节点不关心 DeepSeek、OpenAI 或其他模型的 SDK 差异，后续切模型只改 AI 封装层。
        AiResponse response = aiClient.chat(AiRequest.builder()
                .systemPrompt("你是一名资深 Java 代码评审专家，请给出准确、可执行、结构清晰的评审意见。")
                .prompt(prompt)
                .build());

        // 将模型响应写回上下文，result_parse 节点负责把它转换成最终业务结果。
        context.putVariable(WorkflowVariableKeys.AI_RESPONSE, response);
    }
}
