package com.aidevcopilot.workflow.node;

import com.aidevcopilot.parser.ParsedCode;
import com.aidevcopilot.workflow.constant.WorkflowNodeCodes;
import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 代码解析节点。
 *
 * <p>当前节点作用：将输入代码包装为 ParsedCode，作为后续 Prompt 构建的结构化输入。
 * 第一版不做真实语法树解析，避免过早引入语言解析复杂度。</p>
 * <p>输入：language、inputContent。</p>
 * <p>输出：parsedCode。</p>
 * <p>下一节点：PromptBuildNode。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Component
public class CodeParseNode extends AbstractWorkflowNode {

    /**
     * 返回节点编码。
     *
     * @return 代码解析节点编码
     */
    @Override
    public String nodeCode() {
        return WorkflowNodeCodes.CODE_PARSE;
    }

    /**
     * 执行轻量代码解析。
     *
     * @param context 工作流上下文
     */
    @Override
    public void execute(WorkflowContext context) {
        // 从上下文读取用户输入。这里依赖 input_validate 节点已经完成非空校验。
        String language = requiredVariable(context, WorkflowVariableKeys.LANGUAGE, String.class);
        String inputContent = requiredVariable(context, WorkflowVariableKeys.INPUT_CONTENT, String.class);

        // 第一版暂不做 AST/语法树解析，只把原始输入包装成 ParsedCode。
        // 这样后续如果要替换为 JavaParser、Tree-sitter 等真实解析器，只需要改这个节点。
        ParsedCode parsedCode = ParsedCode.builder()
                .language(language)
                .rawContent(inputContent)
                .metadata(Map.of("parseMode", "placeholder"))
                .build();

        // 把解析结果写回上下文，供 prompt_build 节点继续使用。
        context.putVariable(WorkflowVariableKeys.PARSED_CODE, parsedCode);
    }
}
