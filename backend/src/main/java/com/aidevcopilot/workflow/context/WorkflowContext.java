package com.aidevcopilot.workflow.context;

import com.aidevcopilot.workflow.enums.WorkflowStatusEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 工作流上下文。
 *
 * <p>用于在工作流引擎和各个节点之间传递运行期数据，包括工作流编码、
 * 执行实例 ID、执行状态以及节点读写的变量。第一版使用 Map 保持轻量，
 * 后续可进一步演进为强类型上下文或按业务拆分上下文对象。</p>
 *
 * <p>所属模块：AI Workflow 上下文模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Data
public class WorkflowContext {

    /** 工作流编码，例如 code_review。 */
    private String workflowCode;

    /** 工作流执行实例 ID，用于日志追踪和后续持久化执行记录。 */
    private String workflowRunId = UUID.randomUUID().toString();

    /** 当前工作流执行状态。 */
    private WorkflowStatusEnum status = WorkflowStatusEnum.CREATED;

    /** 节点之间共享的变量集合。 */
    private Map<String, Object> variables = new HashMap<>();

    /**
     * 写入上下文变量。
     *
     * @param key 变量 Key，建议使用 {@code WorkflowVariableKeys} 中的常量
     * @param value 变量值
     */
    public void putVariable(String key, Object value) {
        variables.put(key, value);
    }

    /**
     * 按指定类型读取上下文变量。
     *
     * <p>该方法集中处理类型转换，调用方不需要在节点中重复写强制类型转换。
     * 如果变量不存在则返回 null。</p>
     *
     * @param key 变量 Key
     * @param type 变量目标类型
     * @param <T> 变量目标泛型
     * @return 指定类型的变量值
     * @throws ClassCastException 当变量存在但类型不匹配时抛出
     */
    public <T> T getVariable(String key, Class<T> type) {
        Object value = variables.get(key);
        if (value == null) {
            return null;
        }
        return type.cast(value);
    }
}
