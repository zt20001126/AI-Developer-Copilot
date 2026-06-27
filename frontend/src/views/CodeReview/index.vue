<template>
  <!-- 代码评审页面：绑定表单状态并调用后端工作流接口。 -->
  <div class="page">
    <h1 class="page-title">Code Review</h1>
    <PagePanel>
      <el-form :model="form" label-width="120px">
        <el-form-item label="Language">
          <el-input v-model="form.language" placeholder="Java" clearable />
        </el-form-item>
        <el-form-item label="Input">
          <el-input
            v-model="form.inputContent"
            type="textarea"
            :rows="10"
            placeholder="Paste code or diff here"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="loading" @click="handleSubmit">
            Create Task
          </el-button>
        </el-form-item>
      </el-form>

      <el-divider />

      <el-empty v-if="!result" description="No review result yet" />
      <el-descriptions v-else title="Review Result" :column="1" border>
        <el-descriptions-item label="Status">
          {{ result.status }}
        </el-descriptions-item>
        <el-descriptions-item label="Language">
          {{ result.language }}
        </el-descriptions-item>
        <el-descriptions-item label="Result">
          <pre class="result-content">{{ result.resultContent }}</pre>
        </el-descriptions-item>
      </el-descriptions>
    </PagePanel>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import PagePanel from '../../components/PagePanel.vue'
import { createCodeReviewTask } from '../../api/codeReview'

// 表单状态必须通过 v-model 双向绑定，否则页面无法稳定保存用户输入并提交到后端。
const form = reactive({
  language: 'Java',
  inputContent: 'public class Demo { }'
})

// 请求状态用于控制按钮 loading，避免重复提交。
const loading = ref(false)

// 后端返回的代码评审结果，用于页面展示。
const result = ref(null)

async function handleSubmit() {
  loading.value = true
  try {
    const response = await createCodeReviewTask({
      language: form.language,
      inputContent: form.inputContent
    })
    if (response.code === 0) {
      result.value = response.data
      ElMessage.success('Workflow executed successfully')
      return
    }
    ElMessage.error(response.message || 'Request failed')
  } catch (error) {
    ElMessage.error(error.message || 'Network error')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.result-content {
  margin: 0;
  white-space: pre-wrap;
  word-break: break-word;
  font-family: inherit;
}
</style>
