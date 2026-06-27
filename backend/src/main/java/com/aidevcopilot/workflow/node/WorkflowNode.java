package com.aidevcopilot.workflow.node;

import com.aidevcopilot.workflow.context.WorkflowContext;

/**
 * 工作流节点接口。
 *
 * <p>每个节点只负责一个明确职责，例如参数校验、代码解析、Prompt 构建、
 * AI 调用、结果解析或结果保存。节点通过 WorkflowContext 读取输入并写入输出。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
public interface WorkflowNode {

    /**
     * 返回节点编码。
     *
     * @return 节点唯一编码，用于工作流定义和节点注册表匹配
     */
    String nodeCode();

    /**
     * 执行节点逻辑。
     *
     * @param context 工作流上下文，包含节点输入和中间变量
     */
    void execute(WorkflowContext context);
}
