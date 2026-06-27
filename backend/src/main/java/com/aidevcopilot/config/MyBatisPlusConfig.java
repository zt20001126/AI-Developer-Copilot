package com.aidevcopilot.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置类。
 *
 * <p>用于统一注册 MyBatis-Plus 插件。当前注册分页插件，并指定数据库类型为
 * PostgreSQL，确保后续分页查询生成符合 PostgreSQL 语法的 SQL。</p>
 *
 * <p>注意事项：复杂 SQL 应优先放在 Mapper XML 或专用查询服务中维护，
 * 避免 Controller 或 Service 中拼接 SQL。</p>
 *
 * <p>所属模块：配置模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 创建 MyBatis-Plus 拦截器。
     *
     * @return 已注册 PostgreSQL 分页能力的 MyBatis-Plus 拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 分页插件属于基础设施能力，统一在配置层注册，避免业务代码重复处理分页 SQL。
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        return interceptor;
    }
}

