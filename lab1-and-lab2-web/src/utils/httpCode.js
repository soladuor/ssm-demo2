/**
 * 枚举状态码
 */
const httpCode = {
  SUCCESS: 200, // 成功
  // 错误
  ILLEGAL_REQUEST: 400, // 错误请求
  UNAUTHORIZED: 401, // 未经授权
  FORBIDDEN: 403, // 禁止访问
  NOT_FOUND: 404, // 找不到资源
  PARAM_ERROR: 405, // 参数错误
  DATA_ERROR: 406, // 数据异常
  REPEAT_SUBMIT: 407, // 重复提交
  // 失败
  FAIL: 500, // 失败
  LOGIN_AUTH: 501, // 未登陆
  PERMISSION: 502, // 没有权限
};

export default httpCode;
