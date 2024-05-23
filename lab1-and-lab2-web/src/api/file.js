import { sendRequest } from "../utils/sendRequest";

// 文件请求路径
const base = "/file";

// 用户接口请求
const fileRequest = async ({ url, method, data }) => {
  return await sendRequest({
    url: `${base}${url}`,
    method: method,
    data: data,
  });
};

export const fileApi = {
  // 获取文件列表
  getList: async () => {
    return await fileRequest({
      url: "",
      method: "get",
    });
  },
  delete: async (fileId) => {
    return await fileRequest({
      url: "",
      method: "delete",
      data: {
        fileId: fileId,
      },
    });
  },
  // 文件上传
  upload: async (formData) => {
    return await fileRequest({
      url: "/upload",
      method: "post",
      data: formData,
      // headers: {
      //   "Content-Type": "multipart/form-data",
      // },
      transformRequest: [
        function (data, headers) {
          // 去除post请求默认的Content-Type
          delete headers.post["Content-Type"];
          return data;
        },
      ],
    });
  },
};
