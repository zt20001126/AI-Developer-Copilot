package com.aidevcopilot.workflow;

import com.aidevcopilot.exception.BusinessException;
import com.aidevcopilot.exception.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 默认 AI 工作流引擎。
 *
 * <p>作为 Workflow 模块的统一执行入口，后续负责根据工作流编码加载节点列表，
 * 按顺序执行参数校验、代码解析、AI 调用、结果生成等节点。</p>
 *
 * <p>所属模块：AI Workflow 编排模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Component
public class DefaultAiWorkflowEngine implements AiWorkflowEngine {

    /**
     * 执行 AI 工作流。
     *
     * <p>业务流程说明：后续应在此方法中完成工作流选择、节点编排、
     * 状态流转、异常中断和上下文输出。当前第一阶段仅保留扩展点。</p>
     *
     * @param workflowCode 工作流编码
     * @param context 工作流上下文
     * @return 执行后的工作流上下文
     * @throws BusinessException 当前阶段未实现工作流编排能力时抛出
     */
    @Override
    public WorkflowContext run(String workflowCode, WorkflowContext context) {
        throw new BusinessException(ErrorCode.FEATURE_NOT_IMPLEMENTED, "AI workflow engine is reserved for later phases.");
    }
}
