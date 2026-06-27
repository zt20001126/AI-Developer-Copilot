package com.aidevcopilot.workflow.node;

import com.aidevcopilot.ai.AiResponse;
import com.aidevcopilot.workflow.constant.WorkflowNodeCodes;
import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * AI 调用节点。
 *
 * <p>当前节点作用：承接 Prompt 并生成 AI 响应。第一版为了保证项目无 API Key
 * 也能跑通工作流链路，暂不调用真实模型，只写入占位 AI 响应。</p>
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
public class AiCallNode extends AbstractWorkflowNode {

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
     * 执行 AI 调用占位逻辑。
     *
     * <p>真实模型接入时，应在此节点通过 AiClient 发起调用，不允许绕过 AI 封装层。</p>
     *
     * @param context 工作流上下文
     */
    @Override
    public void execute(WorkflowContext context) {
        requiredVariable(context, WorkflowVariableKeys.PROMPT, String.class);
        AiResponse response = AiResponse.builder()
                .content("第一版工作流已跑通：当前为 AI 代码评审占位结果，尚未调用真实模型。")
                .metadata(Map.of("mock", true))
                .build();
        context.putVariable(WorkflowVariableKeys.AI_RESPONSE, response);
    }
}
