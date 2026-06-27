package com.aidevcopilot.workflow.constant;

/**
 * 工作流节点编码常量。
 *
 * <p>节点编码是工作流定义和节点实现之间的匹配键。集中维护节点编码，
 * 可以降低拼写错误导致运行期找不到节点的风险。</p>
 *
 * <p>所属模块：AI Workflow 常量模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
public final class WorkflowNodeCodes {

    /** 输入参数校验节点。 */
    public static final String INPUT_VALIDATE = "input_validate";

    /** 代码解析节点。 */
    public static final String CODE_PARSE = "code_parse";

    /** Prompt 构建节点。 */
    public static final String PROMPT_BUILD = "prompt_build";

    /** AI 调用节点。 */
    public static final String AI_CALL = "ai_call";

    /** AI 结果解析节点。 */
    public static final String RESULT_PARSE = "result_parse";

    /** 结果保存节点。 */
    public static final String RESULT_SAVE = "result_save";

    private WorkflowNodeCodes() {
    }
}
