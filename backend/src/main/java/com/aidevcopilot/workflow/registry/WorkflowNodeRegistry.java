package com.aidevcopilot.workflow.registry;

import com.aidevcopilot.workflow.exception.WorkflowException;
import com.aidevcopilot.workflow.node.WorkflowNode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 工作流节点注册表。
 *
 * <p>启动时收集 Spring 容器中的所有 WorkflowNode，并按节点编码建立索引。
 * 引擎执行时只依赖该注册表，不依赖具体节点实现，从而降低引擎与业务节点耦合。</p>
 *
 * <p>所属模块：AI Workflow 注册模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Component
public class WorkflowNodeRegistry {

    /** 节点编码到节点 Bean 的映射。 */
    private final Map<String, WorkflowNode> nodeMap;

    /**
     * 创建节点注册表。
     *
     * @param nodes Spring 容器中所有工作流节点
     */
    public WorkflowNodeRegistry(List<WorkflowNode> nodes) {
        this.nodeMap = nodes.stream()
                .collect(Collectors.toMap(WorkflowNode::nodeCode, Function.identity()));
    }

    /**
     * 根据节点编码获取节点。
     *
     * @param nodeCode 节点编码
     * @return 节点实例
     * @throws WorkflowException 节点不存在时抛出
     */
    public WorkflowNode getNode(String nodeCode) {
        WorkflowNode node = nodeMap.get(nodeCode);
        if (node == null) {
            throw new WorkflowException("工作流节点不存在：" + nodeCode);
        }
        return node;
    }
}
