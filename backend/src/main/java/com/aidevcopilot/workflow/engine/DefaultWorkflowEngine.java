package com.aidevcopilot.workflow.engine;

import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import com.aidevcopilot.workflow.definition.WorkflowDefinition;
import com.aidevcopilot.workflow.definition.WorkflowNodeDefinition;
import com.aidevcopilot.workflow.enums.WorkflowStatusEnum;
import com.aidevcopilot.workflow.exception.WorkflowException;
import com.aidevcopilot.workflow.node.WorkflowNode;
import com.aidevcopilot.workflow.registry.WorkflowNodeRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * 默认工作流执行引擎。
 *
 * <p>第一版采用同步线性执行模型：按节点 order 升序执行，任何节点失败都会
 * 中断后续节点，并将工作流状态设置为 FAILED。该实现保持简单，适合当前
 * AI Code Review、Code Explain 等短链路任务。</p>
 *
 * <p>所属模块：AI Workflow 引擎模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultWorkflowEngine implements WorkflowEngine {

    /** 节点注册表，用于根据节点编码查找实际执行节点。 */
    private final WorkflowNodeRegistry nodeRegistry;

    /**
     * 执行工作流。
     *
     * <p>关键流程：
     * 1. 初始化上下文工作流编码和 RUNNING 状态；
     * 2. 按节点顺序查找并执行节点；
     * 3. 全部节点成功后设置 SUCCESS；
     * 4. 任意节点异常时写入错误信息并设置 FAILED。</p>
     *
     * @param definition 工作流定义
     * @param context 工作流上下文
     * @return 执行后的上下文
     * @throws WorkflowException 工作流定义为空或节点执行失败时抛出
     */
    @Override
    public WorkflowContext run(WorkflowDefinition definition, WorkflowContext context) {
        if (definition == null) {
            throw new WorkflowException("工作流定义不能为空");
        }
        if (context == null) {
            throw new WorkflowException("工作流上下文不能为空");
        }

        context.setWorkflowCode(definition.getWorkflowCode());
        context.setStatus(WorkflowStatusEnum.RUNNING);

        try {
            List<WorkflowNodeDefinition> nodeDefinitions = definition.getNodes().stream()
                    .sorted(Comparator.comparing(WorkflowNodeDefinition::getOrder))
                    .toList();

            for (WorkflowNodeDefinition nodeDefinition : nodeDefinitions) {
                WorkflowNode node = nodeRegistry.getNode(nodeDefinition.getNodeCode());
                log.info("Start workflow node. workflowCode={}, runId={}, nodeCode={}",
                        definition.getWorkflowCode(), context.getWorkflowRunId(), nodeDefinition.getNodeCode());
                node.execute(context);
                log.info("Finish workflow node. workflowCode={}, runId={}, nodeCode={}",
                        definition.getWorkflowCode(), context.getWorkflowRunId(), nodeDefinition.getNodeCode());
            }

            context.setStatus(WorkflowStatusEnum.SUCCESS);
            return context;
        } catch (Exception exception) {
            context.setStatus(WorkflowStatusEnum.FAILED);
            context.putVariable(WorkflowVariableKeys.ERROR_MESSAGE, exception.getMessage());
            throw new WorkflowException("工作流执行失败：" + definition.getWorkflowCode(), exception);
        }
    }
}
