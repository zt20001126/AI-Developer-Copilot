package com.aidevcopilot.workflow.node;

import com.aidevcopilot.workflow.WorkflowContext;

/**
 * 工作流节点接口。
 *
 * <p>用于定义 AI Workflow 中单个可执行节点的标准契约。每个节点应只关注
 * 一个明确职责，例如参数校验、代码解析、AI 调用、报告生成或结果持久化，
 * 以保持工作流高内聚、低耦合。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
public interface WorkflowNode {

    /**
     * 返回节点编码。
     *
     * @return 节点唯一编码，用于工作流编排和日志追踪
     */
    String code();

    /**
     * 执行节点逻辑。
     *
     * @param context 工作流上下文，包含节点输入和中间变量
     * @return 执行后的工作流上下文
     * @throws com.aidevcopilot.exception.BusinessException 节点执行失败时抛出
     */
    WorkflowContext execute(WorkflowContext context);
}
