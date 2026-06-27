import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Home/index.vue'
import CodeReviewView from '../views/CodeReview/index.vue'
import ReviewResultView from '../views/ReviewResult/index.vue'

// 基础路由表：第一阶段仅提供页面骨架，不承载业务权限和动态菜单逻辑。
const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/code-review',
    name: 'CodeReview',
    component: CodeReviewView
  },
  {
    path: '/review-result',
    name: 'ReviewResult',
    component: ReviewResultView
  }
]

const router = createRouter({
  // 使用 HTML5 History 模式，保持路径简洁，后续部署时需配置服务端回退到 index.html。
  history: createWebHistory(),
  routes
})

export default router
