package com.NorthKingSys.jbf.cn.biz;

import lombok.Data;

import java.util.Date;

@Data
public class UsrPwdInfo {
    private String user_id;
    private String user_name;
    private Date create_time;
    private String user_role;
    private String role_name;
    private String password;
    private String role_id;
    private String sessionId;
    private String back_up;

    public UsrPwdInfo sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public UsrPwdInfo user_id(String user_id) {
        this.user_id = user_id;
        return this;
    }

    public UsrPwdInfo user_name(String user_name) {
        this.user_name = user_name;
        return this;
    }

    public UsrPwdInfo create_time(Date create_time) {
        this.create_time = create_time;
        return this;
    }

    public UsrPwdInfo user_role(String user_role) {
        this.user_role = user_role;
        return this;
    }

    public UsrPwdInfo role_name(String role_name) {
        this.role_name = role_name;
        return this;
    }

    public UsrPwdInfo password(String password) {
        this.password = password;
        return this;
    }

    public UsrPwdInfo role_id(String role_id) {
        this.role_id = role_id;
        return this;
    }

    public static UsrPwdInfo builder(){
        return new UsrPwdInfo();
    }
}
