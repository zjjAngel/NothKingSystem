package com.NorthKingSys.jbf.cn.domain;

import java.util.Date;

public class JbfUser {
    private Integer id;

    private String userId;

    private String userName;

    private Date createTime;

    private String userRole;

    private String backUp;

    private String signStatus;

    private String userStatus;

    private String lastSignTime;

    private String lastExitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus == null ? null : signStatus.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getLastSignTime() {
        return lastSignTime;
    }

    public void setLastSignTime(String lastSignTime) {
        this.lastSignTime = lastSignTime == null ? null : lastSignTime.trim();
    }

    public String getLastExitTime() {
        return lastExitTime;
    }

    public void setLastExitTime(String lastExitTime) {
        this.lastExitTime = lastExitTime == null ? null : lastExitTime.trim();
    }
}