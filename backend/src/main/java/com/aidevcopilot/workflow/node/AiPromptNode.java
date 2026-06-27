package com.aidevcopilot.workflow.node;

import com.aidevcopilot.common.exception.BusinessException;
import com.aidevcopilot.common.exception.ErrorCode;
import com.aidevcopilot.workflow.WorkflowContext;
import org.springframework.stereotype.Component;

/**
 * AI Prompt 执行节点。
 *
 * <p>当前节点作用：负责在后续代码评审工作流中接收已解析代码和上下文信息，
 * 构建标准化 Prompt，并通过 AI 调用门面发起模型请求。</p>
 *
 * <p>输入：WorkflowContext.variables 中的解析后代码、任务信息和 Prompt 参数。</p>
 * <p>输出：WorkflowContext.variables 中的 AI 原始响应或结构化评审结果。</p>
 * <p>下一节点：后续通常进入报告生成节点，例如 ReportGenerateNode。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Component
public class AiPromptNode implements WorkflowNode {

    /**
     * 返回工作流节点编码。
     *
     * @return 当前节点唯一编码
     */
    @Override
    public String code() {
        return "ai_prompt";
    }

    /**
     * 执行 AI Prompt 节点。
     *
     * <p>Prompt 构建说明：后续应在此节点统一装配系统提示词、用户输入、
     * 代码解析结果和输出格式要求，随后调用 AI 门面，不允许直接在业务代码中
     * 调用模型。</p>
     *
     * @param context 工作流上下文
     * @return 写入 AI 执行结果后的工作流上下文
     * @throws BusinessException 当前阶段未实现节点执行能力时抛出
     */
    @Override
    public WorkflowContext execute(WorkflowContext context) {
        throw new BusinessException(ErrorCode.FEATURE_NOT_IMPLEMENTED, "AI prompt node is reserved for later phases.");
    }
}

