<script setup>
import {UploadFilled, Files} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {userApi} from "@/api/user";
import {ElMessage} from "element-plus";
import router from "@/router";
import httpCode from "@/utils/httpCode";

// 菜单栏
const menu = reactive({
  upload: {name: "文件上传", path: "/upload"},
  download: {name: "文件菜单", path: "/download"},
});
// 当前路径
const path = ref(router.currentRoute.value.fullPath);

// 用户和空间数据
const userMessage = ref({
  userId: 0,
  username: "",
  fileSpaceId: 0,
  fileSpaceSize: 0.0,
});

// 获取用户数据和空间信息
const getUserMessage = async () => {
  const res = await userApi.getUser();
  console.log("res :>> ", res);
  userMessage.value = {...userMessage, ...res.data};
};
getUserMessage();

// 进度颜色
const colors = [
  {color: "#f56c6c", percentage: 20},
  {color: "#e6a23c", percentage: 40},
  {color: "#5cb87a", percentage: 60},
  {color: "#1989fa", percentage: 80},
  {color: "#6f7ad3", percentage: 100},
];

// 精确到两位小数的除法
const division_two = (a, b) => {
  return precision_two(a / b);
};

// 精确到两位小数
const precision_two = (num) => {
  return parseFloat(num.toFixed(2));
};

// 登出
const logout = async () => {
  const res = await userApi.logout();
  if (res.code == httpCode.SUCCESS) {
    router.push("/login");
    ElMessage.success(`已登出`);
    sessionStorage.clear(); // 清空缓存
  }
};
</script>

<template>
  <div class="home-wrapper">
    <el-container>
      <!-- 头部标题栏 -->
      <el-header class="home-wrapper-header">
        <span class="title">Java 框架二 实验</span>
        <div class="user">
          ID：
          <span v-text="userMessage.userId"></span>
          姓名：
          <span v-text="userMessage.username"></span>
          <span> <el-button type="" link @click="logout">退出</el-button></span>
        </div>
      </el-header>
      <el-container class="home-wrapper-container">
        <!-- 侧边菜单栏 -->
        <el-aside width="200px" class="home-wrapper-container-aside">
          <el-menu :default-active="path" :router="true">
            <el-menu-item :index="menu.upload.path">
              <el-icon>
                <UploadFilled/>
              </el-icon>
              <span v-text="menu.upload.name"></span>
            </el-menu-item>
            <el-menu-item :index="menu.download.path">
              <el-icon>
                <Files/>
              </el-icon>
              <span v-text="menu.download.name"></span>
            </el-menu-item>
          </el-menu>
          <div class="space-message">
            <span>空间大小：</span>
            <span
              v-text="division_two(userMessage.fileSpaceSize, 1024 * 1024)"
            ></span>
            <span>M / 5 M</span>
            <el-progress
              :text-inside="true"
              :stroke-width="20"
              :percentage="
                precision_two(
                  (userMessage.fileSpaceSize / (5 * 1024 * 1024)) * 100
                )
              "
              :color="colors"
            />
          </div>
        </el-aside>
        <!-- 主页 -->
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="scss" scoped>
.home-wrapper {
  &-header {
    font-size: large;
    // 两端对齐
    display: flex;
    justify-content: space-between;
    // 上下居中
    align-items: center;
    border-bottom: 2px solid #c8c9cc;

    .user {
      padding-right: 30px;

      span {
        border-bottom: 1px solid #c8c9cc;
        margin-right: 25px;
      }
    }
  }

  &-container {
    &-aside {
      .space-message {
        padding: 5px;
        text-align: center;
        margin-top: 50px;
      }
    }
  }
}
</style>
