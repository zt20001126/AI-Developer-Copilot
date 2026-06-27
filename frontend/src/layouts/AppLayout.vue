<template>
  <!-- 应用主布局：左侧导航负责模块切换，右侧区域渲染当前路由页面。 -->
  <el-container class="app-shell">
    <el-aside width="232px" class="app-aside">
      <div class="brand">AI Developer Copilot</div>
      <el-menu router :default-active="route.path" class="app-menu">
        <el-menu-item index="/">
          <span>Home</span>
        </el-menu-item>
        <el-menu-item index="/code-review">
          <span>Code Review</span>
        </el-menu-item>
        <el-menu-item index="/review-result">
          <span>Review Result</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="app-header">
        <span>AI application platform skeleton</span>
      </el-header>
      <el-main class="app-main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { useRoute } from 'vue-router'

// 当前路由用于同步 Element Plus 菜单的选中状态。
const route = useRoute()
</script>

<style scoped>
.app-shell {
  min-height: 100vh;
}

.app-aside {
  border-right: 1px solid #e5e7eb;
  background: #ffffff;
}

.brand {
  height: 64px;
  display: flex;
  align-items: center;
  padding: 0 20px;
  font-size: 16px;
  font-weight: 700;
  color: #111827;
  border-bottom: 1px solid #e5e7eb;
}

.app-menu {
  border-right: 0;
}

.app-header {
  display: flex;
  align-items: center;
  height: 64px;
  color: #4b5563;
  background: #ffffff;
  border-bottom: 1px solid #e5e7eb;
}

.app-main {
  padding: 0;
  background: #f7f8fa;
}
</style>
