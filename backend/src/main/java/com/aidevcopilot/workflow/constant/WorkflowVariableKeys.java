package com.aidevcopilot.workflow.constant;

/**
 * 工作流上下文变量 Key 常量。
 *
 * <p>工作流节点之间通过 {@code WorkflowContext.variables} 传递数据。
 * 变量 Key 必须统一定义，避免节点之间因字符串不一致导致数据读取失败。</p>
 *
 * <p>所属模块：AI Workflow 常量模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
public final class WorkflowVariableKeys {

    /** 用户提交的代码语言。 */
    public static final String LANGUAGE = "language";

    /** 用户提交的源码、补丁或 Diff 内容。 */
    public static final String INPUT_CONTENT = "inputContent";

    /** 代码解析节点输出的结构化代码对象。 */
    public static final String PARSED_CODE = "parsedCode";

    /** Prompt 构建节点输出的用户提示词。 */
    public static final String PROMPT = "prompt";

    /** AI 调用节点输出的模型响应。 */
    public static final String AI_RESPONSE = "aiResponse";

    /** 结果解析节点输出的最终文本结果。 */
    public static final String FINAL_RESULT = "finalResult";

    /** 工作流执行失败时的错误信息。 */
    public static final String ERROR_MESSAGE = "errorMessage";

    private WorkflowVariableKeys() {
    }
}
