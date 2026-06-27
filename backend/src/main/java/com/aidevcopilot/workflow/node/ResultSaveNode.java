package com.aidevcopilot.workflow.node;

import com.aidevcopilot.workflow.constant.WorkflowNodeCodes;
import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import org.springframework.stereotype.Component;

/**
 * 结果保存节点。
 *
 * <p>当前节点作用：作为结果持久化扩展点。第一版不写数据库，只确认 finalResult
 * 已经存在；后续接入 code_review_task 更新逻辑时，应在此节点统一保存任务结果。</p>
 * <p>输入：finalResult。</p>
 * <p>输出：无新增变量。</p>
 * <p>下一节点：无，当前为代码评审工作流最后一个节点。</p>
 *
 * <p>所属模块：AI Workflow 节点模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Component
public class ResultSaveNode extends AbstractWorkflowNode {

    /**
     * 返回节点编码。
     *
     * @return 结果保存节点编码
     */
    @Override
    public String nodeCode() {
        return WorkflowNodeCodes.RESULT_SAVE;
    }

    /**
     * 执行结果保存占位逻辑。
     *
     * @param context 工作流上下文
     */
    @Override
    public void execute(WorkflowContext context) {
        // 当前第一版还没有持久化工作流结果，因此这里只校验 finalResult 是否已经生成。
        // 后续接入数据库后，这里应更新 code_review_task.result_content 和任务状态。
        requiredVariable(context, WorkflowVariableKeys.FINAL_RESULT, String.class);
    }
}
