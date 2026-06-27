package com.aidevcopilot.workflow.enums;

import lombok.Getter;

/**
 * 工作流节点执行状态枚举。
 *
 * <p>第一版暂不持久化节点状态，但预留该枚举便于第二阶段扩展
 * workflow_node_run 节点执行日志表。</p>
 *
 * <p>所属模块：AI Workflow 枚举模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Getter
public enum WorkflowNodeStatusEnum {

    /** 节点等待执行。 */
    WAITING("WAITING", "等待执行"),

    /** 节点正在执行。 */
    RUNNING("RUNNING", "执行中"),

    /** 节点执行成功。 */
    SUCCESS("SUCCESS", "执行成功"),

    /** 节点执行失败。 */
    FAILED("FAILED", "执行失败"),

    /** 节点被跳过。 */
    SKIPPED("SKIPPED", "已跳过");

    /** 状态编码。 */
    private final String code;

    /** 状态描述。 */
    private final String description;

    WorkflowNodeStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
