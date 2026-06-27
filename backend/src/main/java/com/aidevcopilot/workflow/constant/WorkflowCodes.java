package com.aidevcopilot.workflow.constant;

/**
 * 工作流编码常量。
 *
 * <p>用于集中管理平台内置工作流编码，避免 Service、配置类和测试代码中
 * 直接散落字符串。后续新增 AI Code Explain、AI Unit Test 等流程时，
 * 应在此补充对应编码。</p>
 *
 * <p>所属模块：AI Workflow 常量模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
public final class WorkflowCodes {

    /** AI 代码评审工作流编码。 */
    public static final String CODE_REVIEW = "code_review";

    private WorkflowCodes() {
    }
}
