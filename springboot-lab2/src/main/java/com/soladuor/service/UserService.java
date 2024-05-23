package com.soladuor.service;

import com.soladuor.pojo.FileSpace;
import com.soladuor.pojo.FileUser;

public interface UserService {
    /**
     * 注册用户
     *
     * @param user 用户名和密码
     * @return 用户数据
     */
    FileUser signup(FileUser user);

    /**
     * 用户登录
     *
     * @param user 用户名和密码
     * @return 用户数据
     */
    FileUser login(FileUser user);

    /**
     * 获取用户文件空间详情
     *
     * @param userId 用户编号
     * @return 空间详情
     */
    FileSpace getFileSpace(Integer userId);

    /**
     * 为用户开辟空间
     *
     * @param userId 用户id
     * @return 空间id
     */
    Integer createFileSpace(Integer userId);

}
