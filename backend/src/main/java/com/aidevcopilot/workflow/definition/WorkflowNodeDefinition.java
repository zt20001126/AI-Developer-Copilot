package com.aidevcopilot.workflow.definition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作流节点定义。
 *
 * <p>描述某个工作流中的一个节点，包括节点编码、节点名称和执行顺序。
 * 节点编码用于从节点注册表中查找实际执行 Bean。</p>
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
public class WorkflowNodeDefinition {

    /** 节点编码，必须能匹配某个 WorkflowNode 的 nodeCode。 */
    private String nodeCode;

    /** 节点名称，用于日志和后续前端展示。 */
    private String nodeName;

    /** 节点执行顺序，数值越小越早执行。 */
    private Integer order;
}
