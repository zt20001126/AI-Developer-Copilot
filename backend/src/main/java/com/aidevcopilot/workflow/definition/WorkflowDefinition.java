package com.aidevcopilot.workflow.definition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作流定义。
 *
 * <p>描述一个工作流由哪些节点组成。第一版工作流定义通过配置类注册到
 * Spring 容器中，不依赖数据库；后续可平滑迁移为数据库或 JSON 配置。</p>
 *
 * <p>所属模块：AI Workflow 定义模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowDefinition {

    /** 工作流编码，必须全局唯一。 */
    private String workflowCode;

    /** 工作流名称，用于日志、文档和后续前端展示。 */
    private String workflowName;

    /** 当前工作流包含的节点定义列表。 */
    @Builder.Default
    private List<WorkflowNodeDefinition> nodes = new ArrayList<>();
}
