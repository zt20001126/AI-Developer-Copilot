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
│       │   │   ├── config           # Spring、OpenAPI、Redis、MyBatis-Plus 等配置
│       │   │   ├── controller       # RESTful API 接口层
│       │   │   ├── dto              # 请求参数传输对象
│       │   │   ├── entity           # 数据库实体对象
│       │   │   ├── exception        # 统一异常和错误码
│       │   │   ├── mapper           # MyBatis-Plus 数据访问层
│       │   │   ├── parser           # 代码解析器扩展点
│       │   │   ├── service          # 业务服务接口
│       │   │   ├── service/impl     # 业务服务实现
│       │   │   ├── utils            # 通用工具与统一响应
│       │   │   ├── vo               # 响应视图对象
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

### Controller

Controller 仅负责接收 HTTP 请求、触发参数校验并返回 `Result<T>` 统一响应，不编写业务逻辑。当前包含：

- `HealthController`：服务健康检查接口。
- `CodeReviewTaskController`：代码评审任务 RESTful 接口示例。

### Service

Service 负责业务逻辑边界，包括业务参数校验、任务状态处理、Entity/VO 转换以及 Workflow 触发。当前阶段只保留接口和占位实现，不落具体业务。

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

- `AiWorkflowEngine`：工作流统一执行入口。
- `Workflow`：完整工作流执行契约。
- `WorkflowContext`：节点之间传递输入、输出和中间变量。
- `WorkflowNode`：单个节点的标准接口。
- `AiPromptNode`：AI Prompt 执行节点占位。

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
