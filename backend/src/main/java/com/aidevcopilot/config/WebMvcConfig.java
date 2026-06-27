package com.aidevcopilot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置类。
 *
 * <p>用于管理 Web 层通用配置。当前配置前端开发环境访问后端 API 所需的
 * 跨域规则，后续可扩展拦截器、消息转换器、静态资源映射等能力。</p>
 *
 * <p>注意事项：生产环境建议将允许域名收敛到明确的前端域名，避免长期使用
 * 通配配置。</p>
 *
 * <p>所属模块：配置模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册跨域访问规则。
     *
     * @param registry Spring MVC 跨域配置注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}
