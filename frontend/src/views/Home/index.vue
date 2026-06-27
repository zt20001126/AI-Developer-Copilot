<template>
  <!-- 首页骨架：用于确认基础布局、路由和组件库已正确接入。 -->
  <div class="page">
    <h1 class="page-title">Home</h1>
    <PagePanel>
      <p>AI Developer Copilot project shell is ready.</p>
    </PagePanel>
  </div>
</template>

<script setup>
// PagePanel 提供统一页面容器样式，避免每个页面重复编写边框和背景。
import PagePanel from '../../components/PagePanel.vue'
</script>
