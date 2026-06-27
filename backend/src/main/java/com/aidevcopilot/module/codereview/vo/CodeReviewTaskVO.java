package com.aidevcopilot.module.codereview.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 代码评审任务视图对象。
 *
 * <p>用于向前端返回代码评审任务展示数据。VO 与 Entity 分离，
 * 避免数据库结构直接暴露给接口调用方。</p>
 *
 * <p>所属模块：VO 视图对象模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Data
public class CodeReviewTaskVO {

    /** 任务主键 ID。 */
    private Long id;

    /** 当前任务状态，用于前端展示任务流转进度。 */
    private String status;

    /** 代码语言。 */
    private String language;

    /** 用户提交的代码内容。 */
    private String inputContent;

    /** AI 评审结果内容。 */
    private String resultContent;

    /** 任务创建时间。 */
    private LocalDateTime createdTime;

    /** 任务更新时间。 */
    private LocalDateTime updatedTime;
}

