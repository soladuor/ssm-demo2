package com.soladuor.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Log {

    private String logId;
    // @JsonFormat 要加时区，否则格式化的时间可能会对不上（按系统默认值来的），直接在数据库链接配置中加上?serverTimezone=UTC也可以解决
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 可用于接收前端的时间参数，否则无法解析
    private Date datetime;
    private String desc;

    public Log() {
    }

    public Log(String logId, Date datetime, String desc) {
        this.logId = logId;
        this.datetime = datetime;
        this.desc = desc;
    }

    /**
     * 获取
     *
     * @return logId
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 设置
     *
     * @param logId
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * 获取
     *
     * @return datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * 设置
     *
     * @param datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * 获取
     *
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置
     *
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String toString() {
        return "Log{logId = " + logId + ", datetime = " + datetime + ", desc = " + desc + "}";
    }
}
