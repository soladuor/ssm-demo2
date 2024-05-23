package com.soladuor.service;

import com.soladuor.pojo.FileMessage;
import com.soladuor.pojo.FileSpace;
import com.soladuor.pojo.FileType;

import java.util.List;

public interface FileService {

    /**
     * 获取所有文件信息列表
     *
     * @return 所有文件信息列表
     */
    List<FileMessage> getAllFileList();

    /**
     * 上传文件
     *
     * @param size     文件大小
     * @param userId   上传用户id
     * @param filename 文件名
     * @param typeName 文件类型名
     */
    void upload(String uuid, double size, Integer userId, String filename, String typeName);

    /**
     * 根据用户id查询空间
     *
     * @param userId 用户id
     * @return 空间信息
     */
    FileSpace getFileSpaceIdByUserId(Integer userId);

    /**
     * 根据类型名查询文件类型信息
     *
     * @param typeName
     * @return
     */
    FileType getTypeByName(String typeName);

    /**
     * 根据文件ID查询文件
     *
     * @param fileId 文件ID
     * @return 文件信息
     */
    FileMessage getFileMessageById(String fileId);

    /**
     * 文件下载次数加1
     *
     * @param fileId 文件ID
     */
    void fileDownload(String fileId);

    /**
     * 删除文件
     *
     * @param fileId 文件ID
     */
    int deleteFile(String fileId);

}
