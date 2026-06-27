package com.aidevcopilot.parser;

/**
 * 代码解析器接口。
 *
 * <p>用于将用户提交的源码、补丁或 Diff 内容转换为统一解析结果，
 * 为后续 AI Prompt 构建、规则检查和上下文裁剪提供结构化输入。</p>
 *
 * <p>所属模块：代码解析模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
public interface CodeParser {

    /**
     * 解析代码内容。
     *
     * @param language 代码语言，用于选择语言特定解析策略
     * @param content 用户提交的代码内容
     * @return 标准化代码解析结果
     * @throws com.aidevcopilot.exception.BusinessException 当前阶段解析能力未实现时抛出
     */
    ParsedCode parse(String language, String content);
}
