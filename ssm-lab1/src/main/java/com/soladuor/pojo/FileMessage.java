package com.soladuor.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FileMessage {


    private String id;    // 文件编号

    private Integer spaceId;    // 空间 外键

    private String fileName;    // 真实文件名

    private String filePath;    // 存放文件名（路径）

    private Integer uploaderId;    // 上传人 外键

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date updateTime;    // 上传时间

    private Double fileSize;    // 文件大小（单位字节）

    private Integer downloadCount;    // 下载次数

    private FileType fileType;    // 文件类型 外键

    public FileMessage() {
    }

    public FileMessage(String id, Integer spaceId, String fileName, String filePath, Integer uploaderId, Date updateTime, Double fileSize, Integer downloadCount, FileType fileType) {
        this.id = id;
        this.spaceId = spaceId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploaderId = uploaderId;
        this.updateTime = updateTime;
        this.fileSize = fileSize;
        this.downloadCount = downloadCount;
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "FileMessage{" +
            "id='" + id + '\'' +
            ", spaceId=" + spaceId +
            ", fileName='" + fileName + '\'' +
            ", filePath='" + filePath + '\'' +
            ", uploaderId=" + uploaderId +
            ", updateTime=" + updateTime +
            ", fileSize=" + fileSize +
            ", downloadCount=" + downloadCount +
            ", fileType=" + fileType +
            '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Integer uploaderId) {
        this.uploaderId = uploaderId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
