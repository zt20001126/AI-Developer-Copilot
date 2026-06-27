package com.aidevcopilot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI 文档配置类。
 *
 * <p>用于配置 Swagger/OpenAPI 的基础文档信息，方便前后端协作、
 * 接口联调和后续 API 网关文档聚合。</p>
 *
 * <p>所属模块：配置模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Configuration
public class OpenApiConfig {

    /**
     * 构建 OpenAPI 文档元信息。
     *
     * @return OpenAPI 文档配置对象
     */
    @Bean
    public OpenAPI aiDeveloperCopilotOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI Developer Copilot API")
                        .version("0.0.1")
                        .description("Base APIs for AI Developer Copilot."));
    }
}

