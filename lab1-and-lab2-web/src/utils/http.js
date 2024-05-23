/**
 * 封装的axios
 */
import axios from "axios";
// import store from 'store/user'; // 可以用于校验token
// import router from 'router'; // 用于校验不通过的跳转
import { ElMessage } from "element-plus";
import httpCode from "./httpCode";

// 后端接口
import { backendURL } from "./config";

// 创建axios实例
const http = axios.create({
  baseURL: backendURL,
  timeout: 5000,
  // withCredentials: true,
});

// 请求拦截器
// http.interceptors.request.use((config) => {
//   //  权限校验或者token校验
//   return config;
// });

// 响应拦截器
http.interceptors.response.use(
  (res) => {
    // 成功，http状态码200会到这里(res.status==200)
    const response = res.data;
    // console.log(response);
    // 操作成功（后台响应码为成功）
    if (response.code === httpCode.SUCCESS) {
      // ElMessage.success({
      //   message: res.data.message || "请求成功", // 每次请求都弹，看多了就觉得好烦
      // });
    } else {
      // 这里只在错误的时候弹出（成功的弹出在实际调用的时候，不然每次请求成功都弹出会很烦）
      ElMessage.error({
        message: res.data.message + " : " + res.data.data,
      });
      console.error(res.data.message + " : " + res.data.data);
    }
    return Promise.resolve(res);
  },
  (err) => {
    // 错误
    console.log(err.name + ": ", err.code, err.message, err);
    ElMessage.error({
      message: err.message || "请求发生错误",
    });
    // 这里可以写权限校验和跳转登录页
    return Promise.resolve(err);
  }
);

// // 域请求时是否需要使用凭证
// http.defaults.withCredentials = true;
// 默认导出实例
export default http;
