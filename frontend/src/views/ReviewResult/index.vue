<template>
  <!-- 评审结果页面骨架：后续用于展示 AI 代码评审报告和问题明细。 -->
  <div class="page">
    <h1 class="page-title">Review Result</h1>
    <PagePanel>
      <el-empty description="No review result yet" />
    </PagePanel>
  </div>
</template>

<script setup>
// PagePanel 提供统一页面容器样式，后续将承载评审结果详情组件。
import PagePanel from '../../components/PagePanel.vue'
</script>
