<template>
  <!-- 通用页面内容面板，用于统一基础页面的内容承载样式。 -->
  <section class="page-panel">
    <slot />
  </section>
</template>

<style scoped>
.page-panel {
  width: 100%;
  padding: 20px;
  border: 1px solid #e5e7eb;
  border-radius: 8px;
  background: #ffffff;
}
</style>
