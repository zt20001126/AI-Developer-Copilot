package com.aidevcopilot.workflow;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 工作流上下文。
 *
 * <p>用于在 Workflow 和各个 WorkflowNode 之间传递输入、输出和中间变量。
 * 后续可扩展任务 ID、租户信息、链路追踪 ID、用户信息等运行时上下文。</p>
 *
 * <p>所属模块：AI Workflow 编排模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Data
public class WorkflowContext {

    /** 工作流实例 ID，用于标识一次具体的工作流执行。 */
    private String workflowId;

    /** 工作流变量集合，用于在节点之间传递解析结果、AI 输出和状态信息。 */
    private Map<String, Object> variables = new HashMap<>();
}
