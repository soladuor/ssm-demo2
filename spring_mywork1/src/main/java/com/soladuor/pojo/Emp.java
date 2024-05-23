package com.soladuor.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Emp {

    private String empId;

    // 这种首字母小写，第二个字母大写的 get和set方法名需要为 geteName seteName而不是 getEName setEName
    // 但是lombok和ptg都只能生成属性名首字母大写的get/set方法，因此可以用 @JsonProperty 来标注该属性在JSON中的名称
    // 在属性上标准JsonProperty会导致序列化时多出一个字段，官方文档推荐的方法是在get方法上添加JsonProperty
    // @JsonProperty("eName")
    private String eName;

    public Emp() {
    }

    public Emp(String empId, String eName) {
        this.empId = empId;
        this.eName = eName;
    }

    /**
     * 获取
     *
     * @return empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * 设置
     *
     * @param empId
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * 获取
     *
     * @return eName
     */
    @JsonProperty("eName")
    // @JsonGetter("eName")
    public String getEName() {
        return eName;
    }

    public String geteName() {
        return eName;
    }

    /**
     * 设置
     *
     * @param eName
     */
    public void setEName(String eName) {
        this.eName = eName;
    }

    public String toString() {
        return "Emp{empId = " + empId + ", eName = " + eName + "}";
    }
}
