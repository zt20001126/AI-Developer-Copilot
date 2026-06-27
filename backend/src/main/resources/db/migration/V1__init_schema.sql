-- 初始化 AI Developer Copilot 第一阶段基础表结构。
-- 当前脚本仅创建代码评审任务表，不包含具体业务数据。

CREATE TABLE IF NOT EXISTS code_review_task (
    id BIGINT PRIMARY KEY,
    status VARCHAR(32) NOT NULL,
    language VARCHAR(64),
    input_content TEXT,
    result_content TEXT,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE code_review_task IS '代码评审任务表，用于保存 AI Code Review 的任务输入、执行状态和评审结果';
COMMENT ON COLUMN code_review_task.id IS '主键 ID，由 MyBatis-Plus 分布式 ID 策略生成';
COMMENT ON COLUMN code_review_task.status IS '任务状态，例如 PENDING、RUNNING、SUCCESS、FAILED';
COMMENT ON COLUMN code_review_task.language IS '代码语言，例如 Java、Go、Python，用于选择解析器和 Prompt 模板';
COMMENT ON COLUMN code_review_task.input_content IS '用户提交的源码、补丁或 Diff 内容';
COMMENT ON COLUMN code_review_task.result_content IS 'AI 代码评审生成的结果内容';
COMMENT ON COLUMN code_review_task.created_time IS '任务创建时间，用于审计和列表排序';
COMMENT ON COLUMN code_review_task.updated_time IS '任务更新时间，用于记录状态流转时间';

-- 按任务状态查询列表时使用的索引。
CREATE INDEX IF NOT EXISTS idx_code_review_task_status ON code_review_task (status);

-- 按创建时间排序或筛选任务时使用的索引。
CREATE INDEX IF NOT EXISTS idx_code_review_task_created_time ON code_review_task (created_time);
