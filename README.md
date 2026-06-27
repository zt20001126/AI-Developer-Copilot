# AI Developer Copilot

AI Developer Copilot 是一个基于 Spring AI 的 AI 应用平台基础框架，面向研发效能场景，后续将逐步扩展代码评审、代码解释、单元测试生成、接口文档生成、提交信息生成、SQL 优化、AI Agent 和 AI Workflow 等能力。

当前阶段只搭建企业级项目基础结构，不实现具体业务逻辑。所有业务能力均以占位接口、扩展点和示例代码形式保留，确保项目能够启动、演进路径清晰、模块边界稳定。

## 技术栈

后端：

- Java 21
- Spring Boot 3.5.x
- Spring AI
- MyBatis-Plus
- PostgreSQL
- Redis
- Maven
- Lombok
- Swagger/OpenAPI
- Logback

前端：

- Vue 3
- Vite
- Pinia
- Element Plus
- Axios

## 项目结构

```text
.
├── backend
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java/com/aidevcopilot
│       │   │   ├── ai               # Spring AI 调用统一封装
│       │   │   ├── common           # 统一返回、异常、枚举等公共能力
│       │   │   ├── config           # Spring、OpenAPI、Redis、MyBatis-Plus、自定义属性配置
│       │   │   ├── module           # 业务模块聚合入口
│       │   │   │   ├── codereview   # AI 代码评审业务模块
│       │   │   │   │   ├── controller
│       │   │   │   │   ├── dto
│       │   │   │   │   ├── entity
│       │   │   │   │   ├── mapper
│       │   │   │   │   ├── service
│       │   │   │   │   └── vo
│       │   │   │   └── system       # 系统健康检查等基础接口模块
│       │   │   ├── parser           # 代码解析器扩展点
│       │   │   └── workflow         # AI Workflow 编排模块
│       │   └── resources
│       │       ├── application.yml
│       │       ├── logback-spring.xml
│       │       └── db/migration
│       └── test
└── frontend
    ├── package.json
    ├── vite.config.js
    └── src
        ├── api
        ├── components
        ├── layouts
        ├── router
        ├── stores
        ├── utils
        └── views
            ├── Home
            ├── CodeReview
            └── ReviewResult
```

## 模块说明

### Common

Common 模块承载跨业务模块复用的基础能力，避免每个业务模块重复定义通用对象：

- `common.result.Result`：统一接口响应。
- `common.exception`：统一错误码、业务异常和全局异常处理器。
- `common.enums.CodeReviewTaskStatusEnum`：代码评审任务状态枚举。

### Module

Module 模块按业务域组织代码，避免后续功能增多后所有 Controller、Service、Mapper 按技术层平铺。

当前包含：

- `module.codereview`：代码评审业务模块，内部保持 Controller / Service / Mapper / Entity / DTO / VO 分层。
- `module.system`：系统基础接口模块，例如健康检查。

业务模块内部约束：

- Controller 仅负责接收 HTTP 请求、触发参数校验并返回 `Result<T>`。
- Service 负责业务边界，包括业务参数校验、任务状态处理、Entity/VO 转换以及 Workflow 触发。
- Mapper 仅负责数据访问。

### Workflow

Workflow 负责 AI 流程编排。后续代码评审流程建议按如下节点推进：

```text
参数校验节点
  -> 代码解析节点
  -> Prompt 构建节点
  -> AI 评审节点
  -> 报告生成节点
  -> 结果持久化节点
```

当前已预留：

- `workflow.engine.WorkflowEngine`：工作流统一执行入口。
- `workflow.engine.DefaultWorkflowEngine`：第一版同步线性工作流引擎。
- `workflow.context.WorkflowContext`：节点之间传递输入、输出和中间变量。
- `workflow.definition.WorkflowDefinition`：工作流定义。
- `workflow.definition.WorkflowNodeDefinition`：工作流节点定义。
- `workflow.registry.WorkflowDefinitionRegistry`：工作流定义注册表。
- `workflow.registry.WorkflowNodeRegistry`：工作流节点注册表。
- `workflow.node.WorkflowNode`：单个节点的标准接口。
- `workflow.node.InputValidateNode`：参数校验节点。
- `workflow.node.CodeParseNode`：代码解析节点。
- `workflow.node.PromptBuildNode`：Prompt 构建节点。
- `workflow.node.AiCallNode`：AI 调用占位节点。
- `workflow.node.ResultParseNode`：结果解析节点。
- `workflow.node.ResultSaveNode`：结果保存占位节点。

第一版工作流已经接入 `POST /api/v1/code-review/tasks`。调用该接口会同步执行 `code_review` 工作流，并返回占位评审结果。当前不会调用真实大模型，也不会写入数据库。

### AI

AI 模块统一封装 Spring AI 调用，业务代码不允许直接调用模型。当前已预留：

- `AiClient`：统一 AI 调用门面。
- `SpringAiClient`：Spring AI 适配器占位。
- `AiRequest`：统一 AI 请求对象。
- `AiResponse`：统一 AI 响应对象。

为了保证第一阶段无 API Key 也能启动，`application.yml` 中已排除 OpenAI 自动模型装配。后续接入真实模型时，应在 AI 模块集中打开并管理模型调用。

### Parser

Parser 模块用于代码解析，后续可按语言扩展 Java、Python、SQL、Diff 等解析器。当前已预留 `CodeParser`、`DefaultCodeParser` 和 `ParsedCode`。

### Exception

异常处理模块提供：

- `ErrorCode`：统一错误码。
- `BusinessException`：业务异常。
- `GlobalExceptionHandler`：全局异常处理器。

所有接口异常统一转换为 `Result<T>` 返回，避免堆栈信息直接暴露给前端。

## 数据库说明

初始化脚本：

```text
backend/src/main/resources/db/migration/V1__init_schema.sql
```

当前创建基础表：

```text
code_review_task
```

字段说明：

- `id`：主键 ID。
- `status`：任务状态。
- `language`：代码语言。
- `input_content`：用户提交的代码、补丁或 Diff。
- `result_content`：AI 评审结果。
- `created_time`：创建时间。
- `updated_time`：更新时间。

所有表和字段均已添加 PostgreSQL 中文注释。

## 后端启动

进入后端目录：

```bash
cd backend
```

编译打包：

```bash
mvn -q -DskipTests package
```

启动服务：

```bash
mvn spring-boot:run
```

默认访问地址：

- 健康检查：`http://localhost:8080/api/v1/health`
- Swagger UI：`http://localhost:8080/swagger-ui.html`
- OpenAPI JSON：`http://localhost:8080/v3/api-docs`

默认数据库配置位于：

```text
backend/src/main/resources/application.yml
```

本地默认 PostgreSQL 配置：

- 数据库：`ai_developer_copilot`
- 用户名：`postgres`
- 密码：`postgres`

第一阶段为了方便无数据库环境启动，Hikari 已配置 `initialization-fail-timeout: -1`。

## IDEA 导入说明

如果在 IDEA 中出现 `SpringBootApplication`、`RestController`、`Data`、`TableName`、`BaseMapper` 等导入或注解爆红，优先检查是否按 Maven 工程导入。

推荐方式：

1. 使用 IDEA 打开项目根目录 `AI Developer Copilot`。
2. 在 Maven 面板中确认能看到根工程 `ai-developer-copilot` 和子模块 `backend`。
3. 点击 Maven 面板的 Reload All Maven Projects。
4. Project SDK 选择 JDK 21。
5. Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors 中启用注解处理。
6. 如果 Lombok 相关注解仍爆红，确认 IDEA 已安装并启用 Lombok 插件。

当前根目录已提供聚合 `pom.xml`，用于帮助 IDEA 从项目根目录识别 `backend` Maven 模块。

## 前端启动

进入前端目录：

```bash
cd frontend
```

安装依赖：

```bash
npm install
```

启动开发服务：

```bash
npm run dev
```

默认访问地址：

```text
http://localhost:5173
```

前端已配置 `/api` 代理到后端 `http://localhost:8080`。

## 开发规范

- 遵循阿里巴巴 Java 开发规范。
- Controller 不写业务逻辑。
- Service 负责业务逻辑。
- Workflow 负责 AI 流程编排。
- AI 调用统一通过 `AiClient` 封装。
- DTO、VO、Entity 分离。
- 所有 REST API 返回 `Result<T>`。
- 所有异常统一进入 `GlobalExceptionHandler`。
- 不硬编码业务配置，配置集中放在 `application.yml` 或配置类中。
- 第一阶段不实现具体业务逻辑，只提供可运行、可扩展的基础框架。
