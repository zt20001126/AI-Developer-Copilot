package com.aidevcopilot.workflow.config;

import com.aidevcopilot.workflow.constant.WorkflowCodes;
import com.aidevcopilot.workflow.constant.WorkflowNodeCodes;
import com.aidevcopilot.workflow.definition.WorkflowDefinition;
import com.aidevcopilot.workflow.definition.WorkflowNodeDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 工作流定义配置类。
 *
 * <p>第一版使用代码配置方式声明内置工作流，避免过早引入工作流定义表。
 * 当前内置 AI 代码评审工作流，节点按照线性顺序同步执行。</p>
 *
 * <p>所属模块：AI Workflow 配置模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Configuration
public class WorkflowDefinitionConfig {

    /**
     * AI 代码评审工作流定义。
     *
     * @return 代码评审工作流定义
     */
    @Bean
    public WorkflowDefinition codeReviewWorkflowDefinition() {
        return WorkflowDefinition.builder()
                .workflowCode(WorkflowCodes.CODE_REVIEW)
                .workflowName("AI 代码评审工作流")
                .nodes(List.of(
                        WorkflowNodeDefinition.builder()
                                .nodeCode(WorkflowNodeCodes.INPUT_VALIDATE)
                                .nodeName("参数校验")
                                .order(1)
                                .build(),
                        WorkflowNodeDefinition.builder()
                                .nodeCode(WorkflowNodeCodes.CODE_PARSE)
                                .nodeName("代码解析")
                                .order(2)
                                .build(),
                        WorkflowNodeDefinition.builder()
                                .nodeCode(WorkflowNodeCodes.PROMPT_BUILD)
                                .nodeName("Prompt 构建")
                                .order(3)
                                .build(),
                        WorkflowNodeDefinition.builder()
                                .nodeCode(WorkflowNodeCodes.AI_CALL)
                                .nodeName("AI 调用")
                                .order(4)
                                .build(),
                        WorkflowNodeDefinition.builder()
                                .nodeCode(WorkflowNodeCodes.RESULT_PARSE)
                                .nodeName("结果解析")
                                .order(5)
                                .build(),
                        WorkflowNodeDefinition.builder()
                                .nodeCode(WorkflowNodeCodes.RESULT_SAVE)
                                .nodeName("结果保存")
                                .order(6)
                                .build()
                ))
                .build();
    }
}
