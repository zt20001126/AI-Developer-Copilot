package com.aidevcopilot.workflow.exception;

/**
 * 工作流异常。
 *
 * <p>用于表达工作流定义缺失、节点不存在、节点执行失败等 Workflow 模块内部异常。
 * 统一异常类型可以让引擎集中设置失败状态并将错误信息写入上下文。</p>
 *
 * <p>所属模块：AI Workflow 异常模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
public class WorkflowException extends RuntimeException {

    /**
     * 创建工作流异常。
     *
     * @param message 异常说明
     */
    public WorkflowException(String message) {
        super(message);
    }

    /**
     * 创建带原始异常的工作流异常。
     *
     * @param message 异常说明
     * @param cause 原始异常
     */
    public WorkflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
