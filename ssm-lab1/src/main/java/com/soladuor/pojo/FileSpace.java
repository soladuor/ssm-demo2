package com.soladuor.pojo;

public class FileSpace {


    private Integer id;    // 空间id

    private Integer userId;    // 用户 外键

    private Double spaceSize;    // 空间大小（单位字节）

    public FileSpace() {
    }

    public FileSpace(Integer id, Integer userId, Double spaceSize) {
        this.id = id;
        this.userId = userId;
        this.spaceSize = spaceSize;
    }

    @Override
    public String toString() {
        return "FileSpace{" +
            "id=" + id +
            ", userId=" + userId +
            ", spaceSize=" + spaceSize +
            '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getSpaceSize() {
        return spaceSize;
    }

    public void setSpaceSize(Double spaceSize) {
        this.spaceSize = spaceSize;
    }
}
