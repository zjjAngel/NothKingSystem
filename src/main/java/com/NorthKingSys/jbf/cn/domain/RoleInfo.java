package com.NorthKingSys.jbf.cn.domain;

import lombok.Builder;

import java.util.Date;


public class RoleInfo {

    private Integer id;
    private String ROLE_ID;
    private String ROLE_NAME;

    private String BACK_UP;

    private String FEIGN_IDS;

    public RoleInfo() {
    }

    public RoleInfo(Integer id, String ROLE_ID, String ROLE_NAME, String BACK_UP, String FEIGN_IDS) {
        this.id = id;
        this.ROLE_ID = ROLE_ID;
        this.ROLE_NAME = ROLE_NAME;
        this.BACK_UP = BACK_UP;
        this.FEIGN_IDS = FEIGN_IDS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    public String getBACK_UP() {
        return BACK_UP;
    }

    public void setBACK_UP(String BACK_UP) {
        this.BACK_UP = BACK_UP;
    }

    public String getFEIGN_IDS() {
        return FEIGN_IDS;
    }

    public void setFEIGN_IDS(String FEIGN_IDS) {
        this.FEIGN_IDS = FEIGN_IDS;
    }
}
