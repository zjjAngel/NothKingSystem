package com.NorthKingSys.jbf.cn.domain;

public class JbfUserPwd {
    private String userId;

    private String usreName;

    private String password;

    private String effectDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUsreName() {
        return usreName;
    }

    public void setUsreName(String usreName) {
        this.usreName = usreName == null ? null : usreName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate == null ? null : effectDate.trim();
    }
}