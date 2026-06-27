package com.aidevcopilot.module.codereview.service.impl;

import com.aidevcopilot.module.codereview.dto.CodeReviewTaskCreateDTO;
import com.aidevcopilot.module.codereview.entity.CodeReviewTask;
import com.aidevcopilot.common.enums.CodeReviewTaskStatusEnum;
import com.aidevcopilot.common.exception.BusinessException;
import com.aidevcopilot.common.exception.ErrorCode;
import com.aidevcopilot.module.codereview.mapper.CodeReviewTaskMapper;
import com.aidevcopilot.module.codereview.service.CodeReviewTaskService;
import com.aidevcopilot.module.codereview.vo.CodeReviewTaskVO;
import com.aidevcopilot.workflow.constant.WorkflowCodes;
import com.aidevcopilot.workflow.constant.WorkflowVariableKeys;
import com.aidevcopilot.workflow.context.WorkflowContext;
import com.aidevcopilot.workflow.definition.WorkflowDefinition;
import com.aidevcopilot.workflow.engine.WorkflowEngine;
import com.aidevcopilot.workflow.registry.WorkflowDefinitionRegistry;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 代码评审任务业务服务默认实现。
 *
 * <p>负责代码评审任务的业务编排入口。后续应在本类中完成请求参数业务校验、
 * Entity/VO 数据转换、任务状态初始化、数据库持久化，并调用 AI Workflow
 * 模块执行代码评审流程。</p>
 *
 * <p>关键约束：Controller 不编写业务逻辑；AI 调用不在 Service 中直接发生，
 * Service 只负责触发 Workflow，由 Workflow 节点通过 AI 门面统一调用模型。</p>
 *
 * <p>所属模块：Service 业务模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Service
@RequiredArgsConstructor
public class CodeReviewTaskServiceImpl extends ServiceImpl<CodeReviewTaskMapper, CodeReviewTask>
        implements CodeReviewTaskService {

    /** 工作流执行引擎，用于运行代码评审流程。 */
    private final WorkflowEngine workflowEngine;

    /** 工作流定义注册表，用于根据工作流编码获取流程定义。 */
    private final WorkflowDefinitionRegistry workflowDefinitionRegistry;

    /**
     * 创建代码评审任务。
     *
     * <p>状态流转说明：第一版不持久化任务，但会同步执行 code_review 工作流。
     * 工作流执行成功后，返回 SUCCESS 状态和占位评审结果。</p>
     *
     * <p>数据转换预留：后续应将 {@link CodeReviewTaskCreateDTO} 转换为
     * {@link CodeReviewTask} 持久化，再转换为 {@link CodeReviewTaskVO} 返回。</p>
     *
     * @param request 创建任务请求参数
     * @return 创建后的代码评审任务视图对象
     * @throws com.aidevcopilot.workflow.exception.WorkflowException 工作流执行失败时抛出
     */
    @Override
    public CodeReviewTaskVO createTask(CodeReviewTaskCreateDTO request) {
        WorkflowContext context = new WorkflowContext();
        context.putVariable(WorkflowVariableKeys.LANGUAGE, request.getLanguage());
        context.putVariable(WorkflowVariableKeys.INPUT_CONTENT, request.getInputContent());

        WorkflowDefinition definition = workflowDefinitionRegistry.getDefinition(WorkflowCodes.CODE_REVIEW);
        WorkflowContext resultContext = workflowEngine.run(definition, context);

        CodeReviewTaskVO taskVO = new CodeReviewTaskVO();
        taskVO.setId(IdWorker.getId());
        taskVO.setStatus(CodeReviewTaskStatusEnum.SUCCESS.getCode());
        taskVO.setLanguage(request.getLanguage());
        taskVO.setInputContent(request.getInputContent());
        taskVO.setResultContent(resultContext.getVariable(WorkflowVariableKeys.FINAL_RESULT, String.class));
        taskVO.setCreatedTime(LocalDateTime.now());
        taskVO.setUpdatedTime(LocalDateTime.now());
        return taskVO;
    }

    /**
     * 查询代码评审任务详情。
     *
     * <p>后续应在此完成任务存在性校验、Entity 到 VO 的转换以及必要的权限校验。
     * 当前阶段仅保留接口形态，便于前端和 API 文档提前对齐。</p>
     *
     * @param id 任务主键
     * @return 代码评审任务视图对象
     * @throws BusinessException 当前阶段未实现任务查询能力时抛出
     */
    @Override
    public CodeReviewTaskVO getTask(Long id) {
        throw new BusinessException(ErrorCode.FEATURE_NOT_IMPLEMENTED, "Code review task query is reserved for later phases.");
    }
}

