package com.aidevcopilot.module.codereview.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.aidevcopilot.common.enums.CodeReviewTaskStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 代码评审任务实体。
 *
 * <p>映射数据库表 code_review_task，用于持久化 AI 代码评审任务的基础信息。
 * Entity 只描述数据表结构，不承载接口入参校验或页面展示组装逻辑。</p>
 *
 * <p>所属模块：Entity 数据持久化模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Data
@TableName("code_review_task")
public class CodeReviewTask {

    /** 主键 ID，由 MyBatis-Plus 雪花算法生成。 */
    @TableId
    private Long id;

    /** 当前任务状态，取值来自 {@link CodeReviewTaskStatusEnum#getCode()}。 */
    private String status;

    /** 代码语言，用于代码解析和 AI Prompt 模板选择。 */
    private String language;

    /** 用户提交的代码内容、补丁内容或 Diff 内容。 */
    private String inputContent;

    /** AI 代码评审生成的结果内容。 */
    private String resultContent;

    /** 任务创建时间，用于审计和列表排序。 */
    private LocalDateTime createdTime;

    /** 任务最后更新时间，用于记录状态流转时间。 */
    private LocalDateTime updatedTime;
}

