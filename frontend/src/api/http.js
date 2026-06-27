import axios from 'axios'

// Axios 实例统一封装后端 API 调用，避免页面组件直接散落请求配置。
const http = axios.create({
  // 与 Vite 代理保持一致，开发环境会转发到后端 /api/v1。
  baseURL: '/api/v1',
  // 请求超时时间，避免外部服务异常时页面长时间等待。
  timeout: 10000
})

// 响应拦截器统一返回后端 Result 结构，后续可在此集中处理登录失效和错误提示。
http.interceptors.response.use(
  response => response.data,
  error => Promise.reject(error)
)

export default http
