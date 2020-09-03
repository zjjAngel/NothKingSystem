package com.NorthKingSys.jbf.cn.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class UserInfo {
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

}