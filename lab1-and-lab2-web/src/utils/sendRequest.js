/**
 * 封装的请求
 */
import axios from "./http";
import httpCode from "./httpCode";
import { ElMessage, ElLoading } from "element-plus";
import router from "@/router";

const baseOptions = {
  url: "",
  method: "",
  headers: {},
  data: {},
};

export const sendRequest = async (options) => {
  // 遮罩层
  ElLoading.service(); // 服务的方式调用的全屏 Loading 是单例的

  options = { ...baseOptions, ...options };

  // 转换method为大写，便于处理
  options.method = options.method.toUpperCase();
  if (options.method === "GET" || options.method === "DELETE") {
    options.params = options.data;
    delete options.data;
  }
  // 用户校验(文件上传组件也用到了)
  // const sessionId = sessionStorage.getItem("session");
  // if (sessionId != null) {
  //   options.headers.myAuth = `${sessionId}`;
  // }
  // 发送请求
  try {
    const response = await axios(options);

    if (Number(response.data.code) !== httpCode.SUCCESS) {
      if (Number(response.data.code) === httpCode.UNAUTHORIZED) {
        // 权限不够，返回登录页
        router.push({ path: "/login" });
        sessionStorage.clear(); // 清空缓存
        // return false;
      }
      // return false;
    }

    return response.data;
  } catch (error) {
    console.error("Error occurred while sending request:", error);
    ElMessage.error({
      message: "发送请求时出错：" + error,
    });
    throw error;
  } finally {
    // 遮罩层
    ElLoading.service().close(); // 服务的方式调用的全屏 Loading 是单例的
  }
};
