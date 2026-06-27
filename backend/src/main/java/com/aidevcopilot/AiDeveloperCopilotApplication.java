package com.aidevcopilot;

import com.aidevcopilot.config.properties.CorsProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AI Developer Copilot 后端启动类。
 *
 * <p>负责启动 Spring Boot 应用、加载组件扫描、装配 MyBatis Mapper，
 * 是后端服务的统一入口。当前属于基础框架模块，后续所有业务模块都从
 * 该入口完成 Spring 容器初始化。</p>
 *
 * <p>所属模块：应用启动模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@MapperScan("com.aidevcopilot.module")
@EnableConfigurationProperties(CorsProperties.class)
@SpringBootApplication
public class AiDeveloperCopilotApplication {

    /**
     * 启动后端应用。
     *
     * @param args 命令行启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(AiDeveloperCopilotApplication.class, args);
    }
}

