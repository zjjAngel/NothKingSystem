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
}
