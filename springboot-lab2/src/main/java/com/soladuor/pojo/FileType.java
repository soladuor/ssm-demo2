package com.soladuor.pojo;

public class FileType {


    private Integer id;    // 类型id

    private String typeName;    // 类型名

    public FileType() {
    }

    public FileType(Integer id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "FileType{" +
            "id=" + id +
            ", typeName='" + typeName + '\'' +
            '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
