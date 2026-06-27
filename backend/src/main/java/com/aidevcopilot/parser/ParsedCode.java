package com.aidevcopilot.parser;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * 代码解析结果对象。
 *
 * <p>用于承载解析器输出的标准化信息，避免后续 Workflow 节点直接处理原始输入。
 * 当前仅保留基础字段，后续可扩展文件列表、语法树、变更行号等结构化内容。</p>
 *
 * <p>所属模块：代码解析模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Data
@Builder
public class ParsedCode {

    /** 解析后的代码语言。 */
    private String language;

    /** 原始代码内容，用于保留用户输入和后续审计。 */
    private String rawContent;

    /** 解析扩展信息，例如文件名、行号范围、语法树摘要等。 */
    private Map<String, Object> metadata;
}
