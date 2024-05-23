import { sendRequest } from "../utils/sendRequest";

// 用户请求路径
const base = "/user";

// 用户接口请求
const userRequest = async ({ url, method, data }) => {
  return await sendRequest({
    url: `${base}${url}`,
    method: method,
    data: data,
  });
};

/* 可以传的参数
{
  id,
  username,
  password
}
*/

export const userApi = {
  // 注册
  signup: async ({ id, username, password }) => {
    return await userRequest({
      url: "/signup",
      method: "post",
      data: {
        id: id,
        username: username,
        password: password,
      },
    });
  },
  // 登录
  login: async ({ id, username, password }) => {
    return await userRequest({
      url: "/login",
      method: "post",
      data: {
        id: id,
        username: username,
        password: password,
      },
    });
  },
  // 获取用户数据和空间信息
  getUser: async () => {
    return await userRequest({
      url: "",
      method: "get",
    });
  },
  // 登出
  logout: async () => {
    return await userRequest({
      url: "/logout",
      method: "get",
    });
  },
};
