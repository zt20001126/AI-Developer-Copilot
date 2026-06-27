package com.aidevcopilot.workflow.node;

import com.aidevcopilot.workflow.context.WorkflowContext;
import com.aidevcopilot.workflow.exception.WorkflowException;

/**
 * 工作流节点抽象基类。
 *
 * <p>封装节点常用辅助方法，避免每个节点重复编写变量必填校验、类型读取等代码。
 * 第一版仅提供必填变量读取能力，后续可扩展节点耗时统计、统一日志、重试策略等。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
public abstract class AbstractWorkflowNode implements WorkflowNode {

    /**
     * 从上下文读取必填变量。
     *
     * @param context 工作流上下文
     * @param key 变量 Key
     * @param type 变量类型
     * @param <T> 变量泛型
     * @return 指定类型的变量值
     * @throws WorkflowException 当变量不存在时抛出
     */
    protected <T> T requiredVariable(WorkflowContext context, String key, Class<T> type) {
        T value = context.getVariable(key, type);
        if (value == null) {
            throw new WorkflowException("工作流上下文缺少必要变量：" + key);
        }
        return value;
    }
}
