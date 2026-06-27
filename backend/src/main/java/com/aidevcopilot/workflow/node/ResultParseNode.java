package com.aidevcopilot.workflow.node;

import com.aidevcopilot.ai.AiResponse;
import com.aidevcopilot.workflow.constant.WorkflowNodeCodes;
import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import org.springframework.stereotype.Component;

/**
 * AI 结果解析节点。
 *
 * <p>当前节点作用：将 AI 响应转换为最终评审结果。第一版直接取 AI 响应文本，
 * 后续可扩展 JSON 解析、Markdown 结构化、问题分级和行号映射。</p>
 * <p>输入：aiResponse。</p>
 * <p>输出：finalResult。</p>
 * <p>下一节点：ResultSaveNode。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Component
public class ResultParseNode extends AbstractWorkflowNode {

    /**
     * 返回节点编码。
     *
     * @return 结果解析节点编码
     */
    @Override
    public String nodeCode() {
        return WorkflowNodeCodes.RESULT_PARSE;
    }

    /**
     * 解析 AI 响应。
     *
     * @param context 工作流上下文
     */
    @Override
    public void execute(WorkflowContext context) {
        // ai_call 节点返回的是统一 AiResponse，result_parse 节点负责把模型响应转换为业务最终结果。
        // 当前第一版直接取 content；后续可以在这里解析 JSON、Markdown 或问题列表。
        AiResponse aiResponse = requiredVariable(context, WorkflowVariableKeys.AI_RESPONSE, AiResponse.class);
        context.putVariable(WorkflowVariableKeys.FINAL_RESULT, aiResponse.getContent());
    }
}
