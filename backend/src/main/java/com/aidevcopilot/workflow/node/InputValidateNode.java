package com.aidevcopilot.workflow.node;

import com.aidevcopilot.workflow.constant.WorkflowNodeCodes;
import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import com.aidevcopilot.workflow.exception.WorkflowException;
import org.springframework.stereotype.Component;

/**
 * 输入参数校验节点。
 *
 * <p>当前节点作用：校验工作流上下文中是否存在代码语言和输入内容。</p>
 * <p>输入：language、inputContent。</p>
 * <p>输出：无新增变量，仅在校验失败时中断流程。</p>
 * <p>下一节点：CodeParseNode。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Component
public class InputValidateNode extends AbstractWorkflowNode {

    /**
     * 返回节点编码。
     *
     * @return 参数校验节点编码
     */
    @Override
    public String nodeCode() {
        return WorkflowNodeCodes.INPUT_VALIDATE;
    }

    /**
     * 执行输入参数校验。
     *
     * @param context 工作流上下文
     * @throws WorkflowException 当必要输入为空时抛出
     */
    @Override
    public void execute(WorkflowContext context) {
        // language 和 inputContent 是代码评审流程的最小输入。
        // 如果这里为空，后面的代码解析和 Prompt 构建都没有意义，所以在第一个节点就拦截。
        String language = requiredVariable(context, WorkflowVariableKeys.LANGUAGE, String.class);
        String inputContent = requiredVariable(context, WorkflowVariableKeys.INPUT_CONTENT, String.class);
        if (language.isBlank()) {
            throw new WorkflowException("代码语言不能为空");
        }
        if (inputContent.isBlank()) {
            throw new WorkflowException("代码内容不能为空");
        }
    }
}
