package com.aidevcopilot.common.enums;

import lombok.Getter;

/**
 * 代码评审任务状态枚举。
 *
 * <p>用于集中管理代码评审任务的生命周期状态，避免在业务代码、工作流节点
 * 或数据库转换逻辑中散落字符串常量。后续新增状态时应优先在此扩展，
 * 并同步完善状态流转规则。</p>
 *
 * <p>所属模块：公共枚举模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Getter
public enum CodeReviewTaskStatusEnum {

    /** 任务已创建，等待工作流调度。 */
    PENDING("PENDING", "待执行"),

    /** 任务正在执行 AI 代码评审流程。 */
    RUNNING("RUNNING", "执行中"),

    /** 任务执行成功并已生成评审结果。 */
    SUCCESS("SUCCESS", "执行成功"),

    /** 任务执行失败，需要记录失败原因或允许重试。 */
    FAILED("FAILED", "执行失败");

    /** 状态编码，持久化到数据库。 */
    private final String code;

    /** 状态中文描述，用于日志、文档或前端展示。 */
    private final String description;

    CodeReviewTaskStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
