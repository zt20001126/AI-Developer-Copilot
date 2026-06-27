package com.aidevcopilot.workflow.registry;

import com.aidevcopilot.workflow.definition.WorkflowDefinition;
import com.aidevcopilot.workflow.exception.WorkflowException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 工作流定义注册表。
 *
 * <p>第一版工作流定义由配置类以 Bean 的形式注册，注册表负责按工作流编码
 * 建立索引。后续如果工作流定义改为数据库存储，可替换该注册表的数据来源。</p>
 *
 * <p>所属模块：AI Workflow 注册模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Component
public class WorkflowDefinitionRegistry {

    /** 工作流编码到工作流定义的映射。 */
    private final Map<String, WorkflowDefinition> definitionMap;

    /**
     * 创建工作流定义注册表。
     *
     * @param definitions Spring 容器中所有工作流定义
     */
    public WorkflowDefinitionRegistry(List<WorkflowDefinition> definitions) {
        this.definitionMap = definitions.stream()
                .collect(Collectors.toMap(WorkflowDefinition::getWorkflowCode, Function.identity()));
    }

    /**
     * 根据工作流编码获取定义。
     *
     * @param workflowCode 工作流编码
     * @return 工作流定义
     * @throws WorkflowException 工作流定义不存在时抛出
     */
    public WorkflowDefinition getDefinition(String workflowCode) {
        WorkflowDefinition definition = definitionMap.get(workflowCode);
        if (definition == null) {
            throw new WorkflowException("工作流定义不存在：" + workflowCode);
        }
        return definition;
    }
}
