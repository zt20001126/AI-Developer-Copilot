package com.aidevcopilot.workflow.enums;

import lombok.Getter;

/**
 * 工作流执行状态枚举。
 *
 * <p>用于描述一次工作流运行的整体状态。第一版只维护整体状态，
 * 后续增加节点执行日志时可结合 WorkflowNodeStatusEnum 记录节点级状态。</p>
 *
 * <p>所属模块：AI Workflow 枚举模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Getter
public enum WorkflowStatusEnum {

    /** 工作流上下文已创建，但尚未开始执行。 */
    CREATED("CREATED", "已创建"),

    /** 工作流正在执行。 */
    RUNNING("RUNNING", "执行中"),

    /** 工作流已成功执行完成。 */
    SUCCESS("SUCCESS", "执行成功"),

    /** 工作流执行失败。 */
    FAILED("FAILED", "执行失败");

    /** 状态编码。 */
    private final String code;

    /** 状态描述。 */
    private final String description;

    WorkflowStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
