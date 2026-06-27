import http from './http'

// 创建代码评审任务。当前后端仅保留接口骨架，后续接入真实业务逻辑。
export function createCodeReviewTask(data) {
  return http.post('/code-review/tasks', data)
}

// 查询代码评审任务详情。当前用于前后端接口路径对齐。
export function getCodeReviewTask(id) {
  return http.get(`/code-review/tasks/${id}`)
}
