<template>
  <!-- 代码评审页面骨架：当前仅展示输入表单，不触发真实业务逻辑。 -->
  <div class="page">
    <h1 class="page-title">Code Review</h1>
    <PagePanel>
      <el-form label-width="120px">
        <el-form-item label="Language">
          <el-input placeholder="Java" />
        </el-form-item>
        <el-form-item label="Input">
          <el-input type="textarea" :rows="10" placeholder="Paste code or diff here" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary">Create Task</el-button>
        </el-form-item>
      </el-form>
    </PagePanel>
  </div>
</template>

<script setup>
// PagePanel 提供统一页面容器样式，后续真实表单逻辑会在本页面逐步接入。
import PagePanel from '../../components/PagePanel.vue'
</script>
