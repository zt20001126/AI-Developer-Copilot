package com.aidevcopilot.workflow;

/**
 * AI 工作流引擎接口。
 *
 * <p>负责根据工作流编码选择并执行对应的 AI 工作流。Service 层只发起业务意图，
 * 具体节点编排、状态流转和上下文传递均由 Workflow 模块负责。</p>
 *
 * <p>所属模块：AI Workflow 编排模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
public interface AiWorkflowEngine {

    /**
     * 执行指定工作流。
     *
     * @param workflowCode 工作流编码，例如 code_review_workflow
     * @param context 工作流上下文，包含输入变量和运行过程数据
     * @return 执行后的工作流上下文
     * @throws com.aidevcopilot.common.exception.BusinessException 当前阶段工作流能力未实现时抛出
     */
    WorkflowContext run(String workflowCode, WorkflowContext context);
}

