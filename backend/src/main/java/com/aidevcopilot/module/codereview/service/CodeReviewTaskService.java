package com.aidevcopilot.module.codereview.service;

import com.aidevcopilot.module.codereview.dto.CodeReviewTaskCreateDTO;
import com.aidevcopilot.module.codereview.entity.CodeReviewTask;
import com.aidevcopilot.module.codereview.vo.CodeReviewTaskVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 代码评审任务业务服务接口。
 *
 * <p>负责承接 Controller 转发的代码评审任务业务请求。后续应在 Service 中
 * 完成参数业务校验、实体转换、任务状态初始化，并调用 Workflow 编排 AI 流程。</p>
 *
 * <p>所属模块：Service 业务模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
public interface CodeReviewTaskService extends IService<CodeReviewTask> {

    /**
     * 创建代码评审任务。
     *
     * @param request 创建任务请求参数
     * @return 创建后的任务视图对象
     * @throws com.aidevcopilot.common.exception.BusinessException 当前阶段业务能力未实现时抛出
     */
    CodeReviewTaskVO createTask(CodeReviewTaskCreateDTO request);

    /**
     * 查询代码评审任务详情。
     *
     * @param id 任务主键
     * @return 任务详情视图对象
     * @throws com.aidevcopilot.common.exception.BusinessException 当前阶段业务能力未实现或任务不存在时抛出
     */
    CodeReviewTaskVO getTask(Long id);
}

