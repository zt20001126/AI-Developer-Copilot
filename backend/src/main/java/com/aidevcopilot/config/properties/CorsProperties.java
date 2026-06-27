package com.aidevcopilot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 跨域配置属性。
 *
 * <p>用于将 CORS 允许来源从代码硬编码迁移到 application.yml，便于不同环境
 * 使用不同前端域名配置。生产环境应配置明确域名，避免使用通配策略。</p>
 *
 * <p>所属模块：配置属性模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-27
 */
@Data
@ConfigurationProperties(prefix = "app.cors")
public class CorsProperties {

    /** 允许跨域访问后端 API 的前端来源列表。 */
    private List<String> allowedOriginPatterns = List.of("http://localhost:5173");
}
