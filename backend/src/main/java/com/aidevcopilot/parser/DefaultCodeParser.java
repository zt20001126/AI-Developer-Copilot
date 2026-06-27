package com.aidevcopilot.parser;

import com.aidevcopilot.exception.BusinessException;
import com.aidevcopilot.exception.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 默认代码解析器。
 *
 * <p>作为代码解析模块的默认实现入口，后续可根据 language 分发到 Java、
 * Python、SQL 等语言专属解析器。当前第一阶段仅保留结构，不实现解析逻辑。</p>
 *
 * <p>所属模块：代码解析模块。</p>
 *
 * @author AI Developer Copilot Team
 * @since 2026-06-25
 */
@Component
public class DefaultCodeParser implements CodeParser {

    /**
     * 解析用户提交的代码内容。
     *
     * <p>文件解析关键说明：后续应在此处完成语言识别、代码切片、语法树解析
     * 或 Diff 标准化，解析结果统一写入 {@link ParsedCode}。</p>
     *
     * @param language 代码语言
     * @param content 代码文本
     * @return 标准化代码解析结果
     * @throws BusinessException 当前阶段未实现解析能力时抛出
     */
    @Override
    public ParsedCode parse(String language, String content) {
        throw new BusinessException(ErrorCode.FEATURE_NOT_IMPLEMENTED, "Code parser is reserved for later phases.");
    }
}
