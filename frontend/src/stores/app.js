import { defineStore } from 'pinia'

// 全局应用状态仓库：当前仅保存应用名称，后续可扩展用户信息、主题和全局配置。
export const useAppStore = defineStore('app', {
  state: () => ({
    appName: 'AI Developer Copilot'
  })
})
