package com.aidevcopilot.controller;

import com.aidevcopilot.dto.CodeReviewTaskCreateDTO;
import com.aidevcopilot.service.CodeReviewTaskService;
import com.aidevcopilot.utils.Result;
import com.aidevcopilot.vo.CodeReviewTaskVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AI 代码评审任务接口控制器。
 *
 * <p>负责接收代码评审任务相关 HTTP 请求并返回统一响应结果。
 * Controller 仅做参数接收、校验触发和结果包装，不编写业务逻辑；
 * 具体业务由 {@link CodeReviewTaskService} 承担。</p>
 *
 * <p>所属模块：Controller 接口层。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/code-review/tasks")
public class CodeReviewTaskController {

    /** 代码评审任务业务服务，用于承接 Controller 转发的业务请求。 */
    private final CodeReviewTaskService codeReviewTaskService;

    /**
     * 创建代码评审任务。
     *
     * <p>接口作用：接收用户提交的代码语言和代码内容，创建后续 AI 评审任务。
     * 请求方式：POST /api/v1/code-review/tasks。
     * 请求参数：{@link CodeReviewTaskCreateDTO}，通过请求体传入。
     * 返回结果：统一返回 {@link Result}，数据体为代码评审任务视图对象。</p>
     *
     * @param request 创建代码评审任务请求参数
     * @return 包含任务信息的统一响应结果
     * @throws com.aidevcopilot.exception.BusinessException 当前阶段业务能力未实现时抛出
     */
    @PostMapping
    public Result<CodeReviewTaskVO> createTask(@Valid @RequestBody CodeReviewTaskCreateDTO request) {
        return Result.success(codeReviewTaskService.createTask(request));
    }

    /**
     * 查询代码评审任务详情。
     *
     * <p>接口作用：根据任务主键查询代码评审任务详情。
     * 请求方式：GET /api/v1/code-review/tasks/{id}。
     * 请求参数：路径参数 id。
     * 返回结果：统一返回 {@link Result}，数据体为代码评审任务视图对象。</p>
     *
     * @param id 代码评审任务主键
     * @return 包含任务详情的统一响应结果
     * @throws com.aidevcopilot.exception.BusinessException 当前阶段业务能力未实现时抛出
     */
    @GetMapping("/{id}")
    public Result<CodeReviewTaskVO> getTask(@PathVariable Long id) {
        return Result.success(codeReviewTaskService.getTask(id));
    }
}
