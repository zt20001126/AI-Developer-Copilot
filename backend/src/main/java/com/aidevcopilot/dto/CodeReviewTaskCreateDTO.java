package com.aidevcopilot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建代码评审任务请求 DTO。
 *
 * <p>用于接收前端提交的代码评审任务参数。DTO 只表达接口入参结构，
 * 不承载数据库字段和页面展示字段，便于后续接口演进。</p>
 *
 * <p>所属模块：DTO 参数传输模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Data
public class CodeReviewTaskCreateDTO {

    /** 代码语言，例如 Java、Go、Python，用于后续选择解析器和 Prompt 模板。 */
    @NotBlank(message = "language must not be blank")
    private String language;

    /** 用户提交的源码、补丁或 Diff 内容，是 AI 代码评审的主要输入。 */
    @NotBlank(message = "inputContent must not be blank")
    private String inputContent;
}
