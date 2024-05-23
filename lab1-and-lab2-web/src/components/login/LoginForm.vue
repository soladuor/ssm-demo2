<script setup>
import { userApi } from "@/api/user";
import { reactive, ref } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router";
import httpCode from "@/utils/httpCode";

const userForm = reactive({
  id: "",
  username: "",
  password: "",
});
// 登录按钮
const loginSubmit = async () => {
  const res = await userApi.login(userForm);
  sessionStorage.setItem("session", res.data);
  if (res.code == httpCode.SUCCESS) {
    ElMessage.success(`已登录`);
    router.push("/upload");
    // router.push({ path: "/" });
  }
  console.log(res);
};
// 注册按钮
const signupSubmit = async () => {
  const res = await userApi.signup(userForm);
  sessionStorage.setItem("session", res.data);
  if (res.code == httpCode.SUCCESS) {
    ElMessage.success(`注册成功`);
    loginSubmit(); // 登录
  }
  console.log(res);
};
// 切换登录注册
const checkSign = ref(false);
</script>

<template>
  <div class="login-form-wapper">
    <div class="login-form-wapper-containter">
      <el-form :model="userForm" label-width="90px">
        <el-form-item label="id" prop="id" required>
          <el-input v-model="userForm.id" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="用户名" prop="username" required v-if="checkSign">
          <el-input v-model="userForm.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password" required>
          <el-input
            v-model="userForm.password"
            type="password"
            placeholder="请输入密码"
            show-password
          />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            color="rgba(255, 255, 255, 0.5)"
            @click="loginSubmit"
            v-if="!checkSign"
          >
            登录
          </el-button>
          <el-button
            type="primary"
            color="rgba(255, 255, 255, 0.5)"
            @click="signupSubmit"
            v-if="checkSign"
          >
            注册
          </el-button>
        </el-form-item>
        <el-form-item>
          <el-switch
            v-model="checkSign"
            style="
              --el-switch-off-color: #409eff;
              --el-switch-on-color: #13ce66;
            "
            inactive-text="登录"
            active-text="注册"
          />
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login-form-wapper {
  min-width: 900px;
  height: 100%;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, #f7d1d7, #bfe3f1);

  &-containter {
    width: 30vw;
    min-width: 400px;
    padding: 50px 60px 30px 30px;
    border-radius: 20px;
    border: 1px solid #fff;
    box-shadow: 2px 1px 19px #0000001a;
  }
}
</style>
