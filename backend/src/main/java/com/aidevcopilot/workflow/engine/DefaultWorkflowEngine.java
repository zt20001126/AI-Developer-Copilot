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
        // 工作流定义为空时，引擎不知道要执行哪些节点，必须立即失败。
        if (definition == null) {
            throw new WorkflowException("工作流定义不能为空");
        }
        // 上下文为空时，节点之间无法传递数据，必须立即失败。
        if (context == null) {
            throw new WorkflowException("工作流上下文不能为空");
        }

        // 将本次执行绑定到具体工作流编码，并标记为 RUNNING。
        // 这个状态后续可以持久化到 workflow_run 表，用于前端展示执行进度。
        context.setWorkflowCode(definition.getWorkflowCode());
        context.setStatus(WorkflowStatusEnum.RUNNING);

        try {
            // 工作流定义只描述“有哪些节点”，真正执行前需要按 order 排序，保证流程顺序稳定。
            List<WorkflowNodeDefinition> nodeDefinitions = definition.getNodes().stream()
                    .sorted(Comparator.comparing(WorkflowNodeDefinition::getOrder))
                    .toList();

            for (WorkflowNodeDefinition nodeDefinition : nodeDefinitions) {
                // 根据节点编码从注册表中找到实际的 Spring Bean。
                // 这样引擎只依赖 WorkflowNode 接口，不依赖 CodeParseNode、AiCallNode 等具体类。
                WorkflowNode node = nodeRegistry.getNode(nodeDefinition.getNodeCode());
                log.info("Start workflow node. workflowCode={}, runId={}, nodeCode={}",
                        definition.getWorkflowCode(), context.getWorkflowRunId(), nodeDefinition.getNodeCode());

                // 节点会直接读写同一个 context：上一个节点写入的变量，后一个节点可以继续读取。
                node.execute(context);

                log.info("Finish workflow node. workflowCode={}, runId={}, nodeCode={}",
                        definition.getWorkflowCode(), context.getWorkflowRunId(), nodeDefinition.getNodeCode());
            }

            // 所有节点都执行完成，才认为整个工作流成功。
            context.setStatus(WorkflowStatusEnum.SUCCESS);
            return context;
        } catch (Exception exception) {
            // 任意节点异常都会中断流程，并把错误信息写回上下文，便于后续排查和持久化。
            context.setStatus(WorkflowStatusEnum.FAILED);
            context.putVariable(WorkflowVariableKeys.ERROR_MESSAGE, exception.getMessage());
            throw new WorkflowException("工作流执行失败：" + definition.getWorkflowCode(), exception);
        }
    }
}
