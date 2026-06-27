package com.aidevcopilot.workflow.engine;

import com.aidevcopilot.workflow.context.WorkflowContext;
import com.aidevcopilot.workflow.definition.WorkflowDefinition;

/**
 * 工作流执行引擎接口。
 *
 * <p>负责按照工作流定义执行节点。Service 层只需要构造上下文并调用该接口，
 * 不关心节点查找、节点排序、异常中断和状态维护细节。</p>
 *
 * <p>所属模块：AI Workflow 引擎模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
public interface WorkflowEngine {

    /**
     * 执行指定工作流定义。
     *
     * @param definition 工作流定义
     * @param context 工作流上下文
     * @return 执行后的工作流上下文
     */
    WorkflowContext run(WorkflowDefinition definition, WorkflowContext context);
}
