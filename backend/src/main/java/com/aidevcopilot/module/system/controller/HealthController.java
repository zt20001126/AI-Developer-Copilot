package com.aidevcopilot.module.system.controller;

import com.aidevcopilot.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 服务健康检查接口控制器。
 *
 * <p>用于提供最小化启动验证接口，便于本地开发、部署脚本或网关探活确认
 * 后端服务是否已经正常启动。</p>
 *
 * <p>所属模块：Controller 接口层。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@RestController
@RequestMapping("/api/v1")
public class HealthController {

    /**
     * 查询服务健康状态。
     *
     * <p>接口作用：返回服务运行状态和当前服务器时间。
     * 请求方式：GET /api/v1/health。
     * 请求参数：无。
     * 返回结果：统一返回 {@link Result}，数据体包含 status 和 time。</p>
     *
     * @return 服务健康状态响应
     */
    @GetMapping("/health")
    public Result<Map<String, Object>> health() {
        return Result.success(Map.of(
                "status", "UP",
                "time", LocalDateTime.now()
        ));
    }
}


