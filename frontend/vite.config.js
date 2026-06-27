import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// Vite 基础配置：注册 Vue 插件，并在开发环境将 /api 请求代理到后端服务。
export default defineConfig({
  plugins: [vue()],
  server: {
    // 前端开发服务端口，README 中也以该端口作为默认访问地址。
    port: 5173,
    proxy: {
      '/api': {
        // 后端 Spring Boot 默认地址，避免前端开发时出现跨域调用问题。
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
})
