import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import Upload from "../components/home/Upload.vue";
import Download from "../components/home/Download.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
      children: [
        {
          path: "upload",
          component: Upload,
        },
        {
          path: "download",
          component: Download,
        },
      ],
    },
    {
      path: "/login",
      name: "login",
      // route level code-splitting
      // this generates a separate chunk (Login.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/LoginView.vue"),
    },
  ],
});

// 路由守卫
router.beforeEach(async (to, from) => {
  const authenticated = sessionStorage.getItem("session");
  if (
    // 检查用户是否已登录
    !authenticated &&
    // 避免无限重定向
    to.name !== "login"
  ) {
    // 将用户重定向到登录页面
    return { name: "login" };
  }
});

export default router;
