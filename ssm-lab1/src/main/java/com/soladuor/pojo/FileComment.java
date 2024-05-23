package com.soladuor.pojo;

public class FileComment {


    private Integer id;    // 评论id

    private String fileId;    // 文件id

    private String message;    // 评论信息

    public FileComment() {
    }

    public FileComment(Integer id, String fileId, String message) {
        this.id = id;
        this.fileId = fileId;
        this.message = message;
    }

    @Override
    public String toString() {
        return "FileComment{" +
            "id=" + id +
            ", fileId='" + fileId + '\'' +
            ", message='" + message + '\'' +
            '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
