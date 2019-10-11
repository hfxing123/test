package com.example.common.entry.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 普通用户考勤表（t_attendance）
 */
public class Attendance implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 签到时间
     */
    private Date workTime;

    /**
     * 签到时间字符串
     */
    private String workTimeStr;

    /**
     * 签退时间
     */
    private Date closingTime;
    /**
     * 签退时间字符串
     */
    private String closingTimeStr;
    /**
     * 1:签到  2：签退  3：补签到  4：补签退
     */
    private Integer status;

    /**
     * 上班日期
     */
    private Date currentDate;

    /**
     * 审核id
     */
    private Long auditorId;

    /**
     * 设备id字符串,用“,”隔开
     */
    private String equipmentId;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getWorkTimeStr() {
        return workTimeStr;
    }

    public void setWorkTimeStr(String workTimeStr) {
        this.workTimeStr = workTimeStr;
    }

    public String getClosingTimeStr() {
        return closingTimeStr;
    }

    public void setClosingTimeStr(String closingTimeStr) {
        this.closingTimeStr = closingTimeStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}