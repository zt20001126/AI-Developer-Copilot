package com.aidevcopilot.config;

import org.springframework.context.annotation.Configuration;

/**
 * Redis 配置类。
 *
 * <p>用于预留 Redis 序列化、缓存管理、分布式锁、限流计数等基础能力配置。
 * 当前阶段仅保留配置入口，不引入具体缓存业务逻辑。</p>
 *
 * <p>注意事项：后续缓存 Key 应统一定义，避免在业务代码中硬编码。</p>
 *
 * <p>所属模块：配置模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Configuration
public class RedisConfig {
}

