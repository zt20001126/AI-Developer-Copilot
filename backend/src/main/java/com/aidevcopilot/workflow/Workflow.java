package com.aidevcopilot.workflow;

/**
 * AI 工作流接口。
 *
 * <p>用于定义一个完整 AI 业务流程的执行契约。一个 Workflow 通常由多个
 * WorkflowNode 组成，负责组织节点执行顺序和上下文传递。</p>
 *
 * <p>所属模块：AI Workflow 编排模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
public interface Workflow {

    /**
     * 执行工作流。
     *
     * @param context 工作流上下文，包含流程输入和中间变量
     * @return 执行完成后的工作流上下文
     * @throws com.aidevcopilot.common.exception.BusinessException 工作流执行失败时抛出
     */
    WorkflowContext execute(WorkflowContext context);
}

