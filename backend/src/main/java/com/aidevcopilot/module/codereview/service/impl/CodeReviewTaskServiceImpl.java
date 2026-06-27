package com.aidevcopilot.module.codereview.service.impl;

import com.aidevcopilot.module.codereview.dto.CodeReviewTaskCreateDTO;
import com.aidevcopilot.module.codereview.entity.CodeReviewTask;
import com.aidevcopilot.common.exception.BusinessException;
import com.aidevcopilot.common.exception.ErrorCode;
import com.aidevcopilot.module.codereview.mapper.CodeReviewTaskMapper;
import com.aidevcopilot.module.codereview.service.CodeReviewTaskService;
import com.aidevcopilot.module.codereview.vo.CodeReviewTaskVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class CodeReviewTaskServiceImpl extends ServiceImpl<CodeReviewTaskMapper, CodeReviewTask>
        implements CodeReviewTaskService {

    /**
     * 创建代码评审任务。
     *
     * <p>状态流转预留：后续创建任务时应将任务初始化为 PENDING，持久化后交由
     * Workflow 推进到 RUNNING、SUCCESS 或 FAILED。当前阶段不实现具体业务逻辑。</p>
     *
     * <p>数据转换预留：后续应将 {@link CodeReviewTaskCreateDTO} 转换为
     * {@link CodeReviewTask} 持久化，再转换为 {@link CodeReviewTaskVO} 返回。</p>
     *
     * @param request 创建任务请求参数
     * @return 创建后的代码评审任务视图对象
     * @throws BusinessException 当前阶段未实现任务创建能力时抛出
     */
    @Override
    public CodeReviewTaskVO createTask(CodeReviewTaskCreateDTO request) {
        throw new BusinessException(ErrorCode.FEATURE_NOT_IMPLEMENTED, "Code review task creation is reserved for later phases.");
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

