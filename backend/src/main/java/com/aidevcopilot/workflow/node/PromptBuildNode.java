package com.aidevcopilot.workflow.node;

import com.aidevcopilot.parser.ParsedCode;
import com.aidevcopilot.workflow.constant.WorkflowNodeCodes;
import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import org.springframework.stereotype.Component;

/**
 * Prompt 构建节点。
 *
 * <p>当前节点作用：根据解析后的代码构建 AI 代码评审 Prompt。
 * 第一版只生成基础 Prompt 模板，后续可扩展模板管理、变量渲染和输出格式约束。</p>
 * <p>输入：parsedCode。</p>
 * <p>输出：prompt。</p>
 * <p>下一节点：AiCallNode。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Component
public class PromptBuildNode extends AbstractWorkflowNode {

    /**
     * 返回节点编码。
     *
     * @return Prompt 构建节点编码
     */
    @Override
    public String nodeCode() {
        return WorkflowNodeCodes.PROMPT_BUILD;
    }

    /**
     * 构建代码评审 Prompt。
     *
     * @param context 工作流上下文
     */
    @Override
    public void execute(WorkflowContext context) {
        // prompt_build 不直接读取原始 inputContent，而是读取 code_parse 节点产出的 ParsedCode。
        // 这样以后 ParsedCode 增加文件名、行号、Diff 信息时，Prompt 构建节点可以直接使用结构化数据。
        ParsedCode parsedCode = requiredVariable(context, WorkflowVariableKeys.PARSED_CODE, ParsedCode.class);

        // 这里是当前代码评审能力的核心 Prompt。
        // 后续建议迁移为模板文件或数据库配置，便于不同语言、不同审查类型复用。
        String prompt = """
                请对以下 %s 代码进行基础评审，重点关注：
                1. 代码可读性
                2. 潜在缺陷
                3. 可维护性
                4. 安全风险

                代码内容：
                %s
                """.formatted(parsedCode.getLanguage(), parsedCode.getRawContent());

        // 将构建好的 Prompt 写入上下文，ai_call 节点只负责模型调用，不再关心 Prompt 如何组装。
        context.putVariable(WorkflowVariableKeys.PROMPT, prompt);
    }
}
