package com.aidevcopilot.config;

import org.springframework.context.annotation.Configuration;

/**
 * Spring AI 配置类。
 *
 * <p>用于集中管理大模型相关配置和 Bean 扩展点，包括模型客户端、Prompt 模板、
 * 上下文记忆、工具调用以及后续的 Agent 能力。当前阶段不创建真实模型 Bean，
 * 以保证未配置 API Key 时项目也可以正常启动。</p>
 *
 * <p>注意事项：业务代码不应在配置类外部直接实例化模型客户端，应通过
 * AI 调用封装模块统一访问。</p>
 *
 * <p>所属模块：配置模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Configuration
public class AiConfig {
}
